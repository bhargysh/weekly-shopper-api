package com.reagroup.weeklyshopperapi.recipes.saverecipe


import java.time.OffsetDateTime

import com.reagroup.weeklyshopperapi.models.RecipeCategory
import io.circe.Decoder
import io.circe.generic.semiauto._

case class NewRecipeRequest(category: RecipeCategory,
                            name: String,
                            ingredients: String,
                            instructions: String,
                            duration: Int,
                            link: Option[String],
                            imageLink: Option[String],
                            createdAt: Option[OffsetDateTime],
                            servings: Int)

object NewRecipeRequest {
  implicit val decoder: Decoder[NewRecipeRequest] = deriveDecoder[NewRecipeRequest]
}
