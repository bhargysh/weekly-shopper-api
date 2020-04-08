package com.reagroup.weeklyshopperapi.errors

import io.circe.Json

sealed trait AppError

final case class ConfigError(configEntry: String, configValue: Option[String]) extends AppError

object AppError {

  import cats.Show
  import cats.syntax.show._
  import io.circe._
  import io.circe.generic.semiauto._
  import io.circe.syntax._

  def toThrowable(appError: AppError): Throwable = new RuntimeException(appError.show)

  implicit val showAppError: Show[AppError] = _.asJson.noSpaces

  implicit val appErrorEncoder: Encoder[AppError] = {
    case error @ ConfigError(_, _) => encodeWithMessage("Invalid or missing configuration entry", error)
  }

  implicit private val configErrorEncoder: Encoder[ConfigError] = deriveEncoder[ConfigError]

  private def encodeWithMessage[A](message: String, a: A)(implicit encoder: Encoder[A]): Json = {
    Json.obj("message" -> message.asJson, "errorDetail" -> encoder(a))
  }
}
