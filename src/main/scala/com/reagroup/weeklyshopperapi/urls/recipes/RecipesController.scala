package com.reagroup.weeklyshopperapi.urls.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import com.reagroup.weeklyshopperapi.urls.ErrorHandler
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.{EntityEncoder, Response}

class RecipesController(getRecipes: IO[Vector[Recipe]]) {

  implicit private val recipesResponseEE: EntityEncoder[IO, Vector[Recipe]] = jsonEncoderOf[IO, Vector[Recipe]]

  def fetchAll(): IO[Response[IO]] =
    for {
      errorOrRecipes <- getRecipes.attempt
      response <- errorOrRecipes match {
        case Left(e) => ErrorHandler(e)
        case Right(recipes) => Ok(recipes)
      }
    } yield response

}
