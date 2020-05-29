package com.reagroup.weeklyshopperapi.urls.saverecipe

import cats.data.ValidatedNel
import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.RecipeId
import com.reagroup.weeklyshopperapi.urls.saverecipe.NewRecipeValidator

class SaveRecipeService(saveRecipe: ValidatedRecipe => IO[RecipeId]) {
  def save(newMovieReq: NewRecipeRequest): IO[ValidatedNel[RecipeValidationError, RecipeId]] = {
    val validatedRecipe: ValidatedNel[RecipeValidationError, ValidatedRecipe] = NewRecipeValidator.validate(newMovieReq)
    validatedRecipe.traverse(saveRecipe)
  }
}
