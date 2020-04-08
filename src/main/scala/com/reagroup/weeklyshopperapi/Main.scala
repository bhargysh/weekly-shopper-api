package com.reagroup.weeklyshopperapi

import cats.effect.{Blocker, ExitCode, IO, IOApp, Resource}
import com.reagroup.infrastructure.http4s.AppServer
import com.reagroup.infrastructure.jsonlogger.{ApplicationLog, JsonLogger, Logging}
import com.reagroup.weeklyshopperapi.config.{Config, Deployment, Environment}
import com.reagroup.weeklyshopperapi.db.DataSource
import com.reagroup.weeklyshopperapi.errors.AppError
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object Main extends IOApp {
  private lazy val jsonLogger = JsonLogger(ApplicationLog.logName)

  override def run(args: List[String]): IO[ExitCode] = {
    execute(Deployment)
  }

  def execute(environment: Environment): IO[ExitCode] = {
    val config = Config.fromEnvironment(environment)
    config match {
      case Right(conf) => startServer(conf)
      case Left(error) => handleFailedStartup(error)
    }
  }

  private def transactor(config: Config): Resource[IO, HikariTransactor[IO]] = for {
    connEC <- ExecutionContexts.fixedThreadPool[IO](40) // our connect EC
    txEC <- Blocker[IO]
    transactor <- DataSource(config.dbConfig, connEC, txEC)
  } yield transactor

  private def startServer(config: Config): IO[ExitCode] = {
      transactor(config).use { dbTransactor =>
        for {
          _ <- IO(Logging.configureLog(config.logConfig))
          _ <- jsonLogger.infoWithOptionA("Starting server...", Some(config))
          appRuntime = new AppRuntime(config, dbTransactor)
          server = AppServer(config.port, appRuntime.routes)
          _ <- jsonLogger.info(s"Server started... diagnostic URL here: \nhttp://localhost:${config.port}/diagnostic")
          process <- server.start()
        } yield process
    }
  }

  private def handleFailedStartup(appError: AppError): IO[ExitCode] =
    jsonLogger
      .errorWithOptionA("Failed to start server", Some(appError))
      .map(_ => ExitCode.Error)

}
