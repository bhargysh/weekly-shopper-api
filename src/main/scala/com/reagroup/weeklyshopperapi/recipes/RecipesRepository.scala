package com.reagroup.weeklyshopperapi.recipes

//import java.time.OffsetDateTime

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import doobie.util.transactor.Transactor
import doobie.implicits._
//import doobie.util.meta.Meta

class RecipesRepository(dbxa: Transactor[IO]) {
  def fetchRecipes(): IO[Vector[Recipe]] = {
    RecipesRepository.fetchRecipesQuery().to[Vector].transact(dbxa)
  }
}

object RecipesRepository {

  def fetchRecipesQuery(): doobie.Query0[Recipe] = {
//    implicit val instantMeta: Meta[OffsetDateTime] = doobie.implicits.javatime.JavaOffsetDateTimeMeta

    sql"""
      SELECT id, type, name, ingredients, instruction, duration, link, image_link, created_at, servings FROM recipes
      """
      .query[Recipe]
  }
}
