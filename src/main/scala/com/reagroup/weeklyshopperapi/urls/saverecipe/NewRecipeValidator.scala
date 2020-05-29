package com.reagroup.weeklyshopperapi.urls.saverecipe

import cats.data.ValidatedNel
import cats.implicits._
import com.reagroup.weeklyshopperapi.models.{Ingredients, Instructions, RecipeCategory, RecipeCookTime, RecipeImageLink, RecipeLink, RecipeName, Servings}

object NewRecipeValidator {

  type ErrorOrValid[A] = ValidatedNel[RecipeValidationError, A]

  def validate(newRecipeRequest: NewRecipeRequest): ErrorOrValid[ValidatedRecipe] = {
      val validatedCategory: ErrorOrValid[RecipeCategory] =
        validateRecipeCategory(newRecipeRequest.category)
      val validatedName = validateRecipeName(newRecipeRequest.name)
      val validatedIngredients = validateRecipeIngredients(newRecipeRequest.ingredients)
      val validatedInstructions = validateRecipeInstructions(newRecipeRequest.instructions)
      val validatedDuration = validateRecipeDuration(newRecipeRequest.duration)
      val validatedRecipeLink = validateRecipeLink(newRecipeRequest.link.getOrElse(""))
      val validatedRecipeImageLink = validateRecipeImageLink(newRecipeRequest.imageLink.getOrElse(""))
      val validatedCreatedAt = validateCreatedAt(newRecipeRequest.createdAt.getOrElse(""))
      val validatedServing = validateRecipeServing(newRecipeRequest.servings)

    (
      validatedCategory,
      validatedName,
      validatedIngredients,
      validatedInstructions,
      validatedDuration,
      validatedRecipeLink,
      validatedRecipeImageLink,
      validatedCreatedAt,
      validatedServing
    )
      .mapN(ValidatedRecipe(validatedCategory,
      validatedName,
      validatedIngredients,
      validatedInstructions,
      validatedDuration,
      validatedRecipeLink,
      validatedRecipeImageLink,
      validatedCreatedAt,
      validatedServing))
  }

  private def validateRecipeCategory(category: Int): ValidatedNel[RecipeValidationError, RecipeCategory] =
    if (RecipeCategory.isValid(category)) RecipeCategory.convertFromInt(category).validNel
    else CategoryIsInvalid.invalidNel

  private def validateRecipeName(name: String): ValidatedNel[RecipeValidationError, RecipeName] =
    if (name.nonEmpty) RecipeName(name).validNel else FieldIsRequired.invalidNel

  private def validateRecipeIngredients(ingredients: String): ValidatedNel[RecipeValidationError, Ingredients] =
    if (ingredients.nonEmpty) Ingredients(ingredients).validNel else FieldIsRequired.invalidNel

  private def validateRecipeInstructions(instructions: String): ValidatedNel[RecipeValidationError, Instructions] =
    if (instructions.nonEmpty) Instructions(instructions).validNel else FieldIsRequired.invalidNel

  private def validateRecipeDuration(duration: Int): ValidatedNel[RecipeValidationError, RecipeCookTime] =
    if (duration < 0) RecipeCookTime(duration).validNel else RecipeCookTimeIsInvalid.invalidNel

  private def validateRecipeLink(link: String): ValidatedNel[RecipeValidationError, Option[RecipeLink]] =
    if (link.nonEmpty) Some(RecipeLink(link)).validNel else None.validNel

  private def validateRecipeImageLink(link: String): ValidatedNel[RecipeValidationError, Option[RecipeImageLink]] =
    if (link.nonEmpty) Some(RecipeImageLink(link)).validNel else None.validNel

  private def validateCreatedAt(dateTimeStr: String): ValidatedNel[RecipeValidationError, Option[String]] = //TODO: Not sure about this
    if (dateTimeStr.nonEmpty && dateTimeStr.contentEquals("now()")) Some(dateTimeStr).validNel else None.validNel

  private def validateRecipeServing(serving: Int): ValidatedNel[RecipeValidationError, Servings] =
    if (serving < 0) Servings(serving).validNel else RecipeServingAmountIsInvalid.invalidNel
}
