package com.reagroup.weeklyshopperapi.config

import com.reagroup.weeklyshopperapi.errors.{AppError, ConfigError, ErrorOr}

class EnvironmentVariables(environment: Environment) {

  private val deploymentDefaults = Map(
    "LOG_LEVEL" -> "info"
  )

  private val developmentDefaults: Map[String, String] = deploymentDefaults ++ Map(
    "APP_NAME" -> "weekly-shopper-api-dev",
    "PORT" -> "8080",
    "VERSION" -> "0.0.1"
  )

  private val defaults: Map[Environment, Map[String, String]] = Map(
    Development -> developmentDefaults,
    Deployment -> deploymentDefaults
  )

  private val environmentVariables = defaults(environment) ++ sys.env

  def lookup(name: String): ErrorOr[String] = {
    environmentVariables.get(name) match {
      case Some(value) => Right[AppError, String](value)
      case None => Left[AppError, String](ConfigError(name, None))
    }
  }
}

sealed trait Environment

object Development extends Environment
object Deployment extends Environment
