package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import org.http4s.dsl.io._
import org.http4s.{HttpRoutes, Response}

class RecipesRoutes(recipesHandler: IO[Response[IO]]) {
  private val RecipesRoute = Root / "recipes"

  implicit val openRoutes: HttpRoutes[IO] = HttpRoutes.of {
    case GET -> RecipesRoute => recipesHandler
  }
}
