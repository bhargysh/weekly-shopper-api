package com.reagroup.weeklyshopperapi.recipes.bycategory

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.{Recipe, RecipeCategory}
import com.reagroup.weeklyshopperapi.recipes.ErrorHandler
import org.http4s.{EntityEncoder, Response}
import org.http4s.circe._
import org.http4s.dsl.io._

class RecipesByCategoryController(fetchRecipesByCategory: RecipeCategory => IO[Vector[Recipe]]) {
  implicit private val recipesByCategoryResponseEE: EntityEncoder[IO, Vector[Recipe]] = jsonEncoderOf[IO, Vector[Recipe]]

  def fetchRecipes(category: RecipeCategory): IO[Response[IO]] =
    for {
      errorOrRecipes <- fetchRecipesByCategory.apply(category).attempt
      response <- errorOrRecipes match {
        case Left(e) => ErrorHandler(e)
        case Right(recipes) => Ok(recipes)
      }
    } yield response
}
