package com.reagroup.weeklyshopperapi

import cats.effect.IO
import org.http4s.dsl.io._
import org.http4s.{EntityEncoder, HttpRoutes}
import org.http4s.circe.jsonEncoderOf

object AppRoutes {
  implicit private val vectorOfStringToJsonToEE: EntityEncoder[IO, Vector[String]] =
    jsonEncoderOf[IO, Vector[String]]

  val routes: HttpRoutes[IO] = HttpRoutes.of {
    case GET -> Root / "recipe" => Ok(Vector.empty[String])
  }
}
