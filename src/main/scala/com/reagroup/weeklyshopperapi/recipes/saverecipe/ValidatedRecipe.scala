package com.reagroup.weeklyshopperapi.recipes.saverecipe

import java.time.OffsetDateTime

import com.reagroup.weeklyshopperapi.models.RecipeCategory

case class ValidatedRecipe(category: RecipeCategory,
                           name: String,
                           ingredients: String,
                           instructions: String,
                           duration: Int,
                           link: Option[String],
                           imageLink: Option[String],
                           createdAt: Option[OffsetDateTime],
                           servings: Int)
