package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import org.http4s.{EntityEncoder, Response}
import org.http4s.circe._
import org.http4s.dsl.io._

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
