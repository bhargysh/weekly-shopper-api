package com.reagroup.weeklyshopperapi.urls.saverecipe

import java.time.OffsetDateTime

import com.reagroup.weeklyshopperapi.models._

final case class ValidatedRecipe(
  category: RecipeCategory,
  name: RecipeName,
  ingredients: Ingredients,
  instructions: Instructions,
  duration: RecipeCookTime,
  link: Option[RecipeLink],
  imageLink: Option[RecipeImageLink],
  createdAt: Option[OffsetDateTime],
  servings: Servings
)
