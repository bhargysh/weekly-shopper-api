package com.reagroup.weeklyshopperapi.urls.saverecipe

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.RecipeId
import doobie.free.connection.ConnectionIO
import doobie.util.transactor.Transactor
import doobie.implicits._

class SaveRecipeRepository(dbxa: Transactor[IO]) {
  def saveRecipe(recipe: ValidatedRecipe): IO[RecipeId] = {
    val insertRecipe: ConnectionIO[RecipeId] =
      for {
        recipeId <- sql"""
                        INSERT INTO recipes(type, name, ingredients, instruction, duration, link, image_link, created_at, servings)
                        VALUES (${recipe.category}, ${recipe.name}, ${recipe.ingredients}, ${recipe.instructions}. ${recipe.duration}. ${recipe.link}, ${recipe.imageLink}, ${recipe.createdAt}, ${recipe.servings})
                        RETURNING id
                      """.query[RecipeId].unique
      } yield recipeId

    insertRecipe.transact(dbxa)
  }
}
