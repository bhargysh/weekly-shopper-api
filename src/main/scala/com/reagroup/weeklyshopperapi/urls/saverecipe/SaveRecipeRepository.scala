package com.reagroup.weeklyshopperapi.urls.saverecipe

import java.time.OffsetDateTime

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.RecipeId
import doobie.free.connection.ConnectionIO
import doobie.util.transactor.Transactor
import doobie.implicits._
import doobie.util.meta.Meta

class SaveRecipeRepository(dbxa: Transactor[IO]) {
//  implicit val instantMeta: Meta[OffsetDateTime] = doobie.implicits.javatime.JavaOffsetDateTimeMeta

  def saveRecipe(recipe: ValidatedRecipe): IO[RecipeId] = {
    val insertRecipe: ConnectionIO[RecipeId] =
      for {
        recipeId <- sql"""
                        INSERT INTO recipes(type, name, ingredients, instruction, duration, link, image_link, created_at, servings)
                        VALUES (${recipe.category}, ${recipe.name}, ${recipe.ingredients}, ${recipe.instructions}. ${recipe.duration}. ${recipe.link.getOrElse("")}, ${recipe.imageLink.getOrElse("")}, ${recipe.createdAt.getOrElse("now()")}, ${recipe.servings})
                        RETURNING id
                      """.query[RecipeId].unique
      } yield recipeId

    insertRecipe.transact(dbxa)
  }
}
