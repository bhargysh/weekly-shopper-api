package com.reagroup.weeklyshopperapi.recipes.saverecipe

import cats.data.ValidatedNel
import cats.implicits._
import com.reagroup.weeklyshopperapi.models.RecipeCategory

object NewRecipeValidator {

  def validate(newRecipeRequest: NewRecipeRequest): ValidatedNel[RecipeValidationError, ValidatedRecipe] = ???

  private def validateRecipeName(name: String): ValidatedNel[RecipeValidationError, String] =
    if (name.nonEmpty) name.validNel else FieldIsRequired.invalidNel

  private def validateRecipeCategory(category: RecipeCategory): ValidatedNel[RecipeValidationError, RecipeCategory] =
    if (???) category.validNel else CategoryIsInvalid.invalidNel


}
