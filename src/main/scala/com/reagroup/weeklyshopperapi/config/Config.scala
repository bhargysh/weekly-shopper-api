package com.reagroup.weeklyshopperapi.config

import com.reagroup.infrastructure.jsonlogger.LogConfig
import com.reagroup.weeklyshopperapi.errors.{AppTry, ConfigError, ErrorOr}
import io.circe.generic.semiauto.deriveEncoder
import io.circe.syntax._
import io.circe.{Encoder, Json}

object Config {

  def fromEnvironment(environment: Environment): ErrorOr[Config] = {
    val environmentVariables = new EnvironmentVariables(environment)
    for {
      port <- environmentVariables
        .lookup("PORT")
        .flatMap(portStr =>
          AppTry(portStr.toInt).left.map(_ =>
            ConfigError("PORT", Some(portStr))))
      version <- environmentVariables.lookup("VERSION")
      logLevel <- environmentVariables.lookup("LOG_LEVEL")
      appName <- environmentVariables.lookup("APP_NAME")
      dbHost <- environmentVariables.lookup("DATABASE_HOST")
      dbName <- environmentVariables.lookup("DATABASE_NAME")
      dbUserName <- environmentVariables.lookup("DATABASE_USERNAME")
      dbPassword <- environmentVariables.lookup("DATABASE_PASSWORD")
    } yield
      Config(
        port = port,
        version = version,
        appName = appName,
        logConfig = LogConfig(logLevel, withAccessLog = true),
        dbConfig = DatabaseConfig(
          url = s"jdbc:postgresql://$dbHost/$dbName",
          userName = dbUserName,
          password = dbPassword
        )
      )
  }
  implicit val configEncoder: Encoder[Config] = deriveEncoder[Config]
  implicit val databaseConfigEncoder: Encoder[DatabaseConfig] = databaseConfig =>
    Json.obj(
      "url" -> databaseConfig.url.asJson
    )
}

final case class Config(
    port: Int,
    version: String,
    appName: String,
    logConfig: LogConfig,
    dbConfig: DatabaseConfig
)

final case class DatabaseConfig(
    url: String,
    userName: String,
    password: String
)
