package com.reagroup.weeklyshopperapi.recipes.saverecipe

import cats.data.ValidatedNel
import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.RecipeId

class SaveRecipeService(saveMovie: ValidatedRecipe => IO[RecipeId]) {

  def save(newRecipeReq: NewRecipeRequest): IO[ValidatedNel[Error, RecipeId]] = {

    val errorsOrValidMovie: ValidatedNel[Error, ValidatedRecipe] = NewRecipeValidator.validate(newRecipeReq)
    ???
  }

}
