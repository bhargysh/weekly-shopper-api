package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import org.http4s.circe.jsonEncoderOf
import org.http4s.dsl.io._
import org.http4s.{EntityEncoder, HttpRoutes}

class RecipesRoutes {
  private val RecipesRoute = Root / "recipes"
  implicit private val vectorOfStringToJsonToEE: EntityEncoder[IO, Vector[String]] =
    jsonEncoderOf[IO, Vector[String]]

  implicit val openRoutes: HttpRoutes[IO] = HttpRoutes.of {
    case GET -> RecipesRoute => Ok(Vector.empty[String])
  }
}
