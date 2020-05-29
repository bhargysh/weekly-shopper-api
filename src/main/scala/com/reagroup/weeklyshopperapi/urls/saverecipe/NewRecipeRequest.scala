package com.reagroup.weeklyshopperapi.urls.saverecipe

import io.circe.Decoder
import io.circe.generic.semiauto._

final case class NewRecipeRequest(
  category: Int,
  name: String,
  ingredients: String,
  instructions: String,
  duration: Int,
  link: Option[String],
  imageLink: Option[String],
  createdAt: Option[String],
  servings: Int
)

object NewRecipeRequest {
  implicit val decoder: Decoder[NewRecipeRequest] = deriveDecoder[NewRecipeRequest]
}
