package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import doobie.util.transactor.Transactor
import doobie.implicits._


class RecipesRepository(dbxa: Transactor[IO]) {
  def fetchRecipes(): IO[Vector[Recipe]] = {
    RecipesRepository.fetchRecipesQuery().to[Vector].transact(dbxa)
  }
}

object RecipesRepository {

  private def fetchRecipesQuery(): doobie.Query0[Recipe] = {
    sql"""
      SELECT id, type, name, ingredients, instruction, duration, link, image_link, created_at, servings FROM recipes
      """
      .query[Recipe]
  }
}
