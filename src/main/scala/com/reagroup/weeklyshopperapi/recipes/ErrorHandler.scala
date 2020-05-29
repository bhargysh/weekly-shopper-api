package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.errors.{AppError, ConfigError, DatabaseError}
import io.circe.Json
import org.http4s.Response
import org.http4s.dsl.Http4sDsl
import io.circe.syntax._
import org.http4s.circe.CirceEntityCodec._

object ErrorHandler extends Http4sDsl[IO] {

  def apply(e: Throwable): IO[Response[IO]] =
    e match {
      case err: AppError => encodeAppError(err)
      case err => InternalServerError(Json.obj("error" -> s"Unexpected error has occurred: ${err.getMessage}".asJson))
    }

  private def encodeAppError(appError: AppError): IO[Response[IO]] =
    appError match {
      case ConfigError(entry, value) => InternalServerError(Json.obj("error" -> s"Failed to load config with entry: $entry and value: $value".asJson))
      case DatabaseError(message) => InternalServerError(Json.obj("error" -> s"Failed to fetch query from db: $message".asJson))
    }

}
