package com.reagroup.weeklyshopperapi.errors

sealed trait AppError extends Throwable

final case class ConfigError(configEntry: String, configValue: Option[String]) extends AppError
final case class DatabaseError(message: String) extends AppError

object AppError {

  import cats.Show
  import io.circe._
  import io.circe.generic.semiauto._
  import io.circe.syntax._

//  def toThrowable(appError: AppError): Throwable = new RuntimeException(appError.show)

  implicit val showAppError: Show[AppError] = _.asJson.noSpaces

  implicit val appErrorEncoder: Encoder[AppError] = {
    case error @ ConfigError(_, _) => encodeWithMessage("Invalid or missing configuration entry", error)
    case error @ DatabaseError(_) => encodeWithMessage("Invalid database query", error)
  }

  implicit private val configErrorEncoder: Encoder[ConfigError] = deriveEncoder[ConfigError]
  implicit private val dbErrorEncoder: Encoder[DatabaseError] = deriveEncoder[DatabaseError]

  private def encodeWithMessage[A](message: String, a: A)(implicit encoder: Encoder[A]): Json = {
    Json.obj("message" -> message.asJson, "errorDetail" -> encoder(a))
  }
}
