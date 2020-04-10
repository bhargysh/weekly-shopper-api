package com.reagroup.weeklyshopperapi.recipes.bycategory

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.{Recipe, RecipeCategory}
import org.http4s.{EntityEncoder, Response}
import org.http4s.circe._
import org.http4s.dsl.io._

class RecipesByCategoryController(fetchRecipesByCategory: RecipeCategory => IO[Vector[Recipe]]) {
  implicit private val recipesByCategoryResponseEE: EntityEncoder[IO, Vector[Recipe]] = jsonEncoderOf[IO, Vector[Recipe]]

  def recipesByCategoryHandler(category: RecipeCategory): IO[Response[IO]] =
    fetchRecipesByCategory(category).flatMap(Ok(_))

}
