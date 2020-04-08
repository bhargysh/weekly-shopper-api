package com.reagroup.weeklyshopperapi

import cats.effect.{ExitCode, IO, IOApp}
import com.reagroup.infrastructure.http4s.AppServer
import com.reagroup.infrastructure.jsonlogger.{ApplicationLog, JsonLogger, Logging}
import com.reagroup.weeklyshopperapi.config.{Config, Deployment, Environment}
import com.reagroup.weeklyshopperapi.errors.AppError

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

  private def startServer(config: Config): IO[ExitCode] = {
    for {
      _ <- IO(Logging.configureLog(config.logConfig))
      _ <- jsonLogger.infoWithOptionA("Starting server...", Some(config))
      appRuntime = new AppRuntime(config)
      server = AppServer(config.port, appRuntime.routes)
      _ <- jsonLogger.info(s"Server started... diagnostic URL here: \nhttp://localhost:${config.port}/diagnostic")
      process <- server.start()
    } yield process
  }

  private def handleFailedStartup(appError: AppError): IO[ExitCode] =
    jsonLogger
      .errorWithOptionA("Failed to start server", Some(appError))
      .map(_ => ExitCode.Error)

}
