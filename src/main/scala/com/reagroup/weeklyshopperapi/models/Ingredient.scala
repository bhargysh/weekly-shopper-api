package com.reagroup.weeklyshopperapi.models

import io.circe.{Encoder, Json}
import io.circe.syntax._

case class Ingredient(amount: Option[IngredientAmount], metric: Option[Metric], name: IngredientName)

object Ingredient {
  implicit val encoder: Encoder[Ingredient] = ingredient =>
    Json.obj(
    "amount" -> ingredient.amount.asJson,
      "metric" -> ingredient.metric.asJson,
      "name" -> ingredient.name.asJson
  )
}
