package com.reagroup.weeklyshopperapi.urls.saverecipe

import io.circe.Encoder
import io.circe.syntax._

sealed trait RecipeValidationError

case object FieldIsRequired extends RecipeValidationError

case object CategoryIsInvalid extends RecipeValidationError

case object RecipeCookTimeIsInvalid extends RecipeValidationError

case object RecipeServingAmountIsInvalid extends RecipeValidationError

object RecipeValidationError {
  implicit val encoder: Encoder[RecipeValidationError] = Encoder {
    case FieldIsRequired => "This field is required".asJson
    case CategoryIsInvalid => "This category is invalid".asJson
    case RecipeCookTimeIsInvalid => "Invalid cook time".asJson
    case RecipeServingAmountIsInvalid => "Invalid servings amount".asJson
  }
}
