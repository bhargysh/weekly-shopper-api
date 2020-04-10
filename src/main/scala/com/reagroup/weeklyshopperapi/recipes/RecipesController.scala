package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import org.http4s.{EntityEncoder, Response}
import org.http4s.circe._
import org.http4s.dsl.io._

class RecipesController(getRecipes: IO[Vector[Recipe]]) {

  implicit private val recipesResponseEE: EntityEncoder[IO, Vector[Recipe]] = jsonEncoderOf[IO, Vector[Recipe]]

  def handleGetRecipes(): IO[Response[IO]] = {
    getRecipes.flatMap { recipes =>
      Ok(recipes)
    }
  }

}
