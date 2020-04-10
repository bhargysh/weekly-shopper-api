package com.reagroup.weeklyshopperapi.recipes.bycategory

import java.time.OffsetDateTime

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.{Recipe, RecipeCategory}
import doobie.util.transactor.Transactor
import doobie.implicits._
import doobie.util.meta.Meta

class RecipesByCategoryRepository(dbxa: Transactor[IO]) {
  def fetchRecipesByCategory(category: RecipeCategory): IO[Vector[Recipe]] =
    RecipesByCategoryRepository.fetchRecipesByCategoryQuery(category).to[Vector].transact(dbxa)
}

object RecipesByCategoryRepository {
  implicit val instantMeta: Meta[OffsetDateTime] = doobie.implicits.javatime.JavaOffsetDateTimeMeta

  private def fetchRecipesByCategoryQuery(category: RecipeCategory): doobie.Query0[Recipe] = {
    sql"""
      SELECT id, type, name, ingredients, instruction, duration, link, image_link, created_at, servings FROM recipes
      WHERE type LIKE $category
      """
      .query[Recipe]
  }
}
