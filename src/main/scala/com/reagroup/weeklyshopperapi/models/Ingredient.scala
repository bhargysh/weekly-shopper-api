package com.reagroup.weeklyshopperapi.models

import doobie.util.{Read, Write}
import io.circe.{Encoder, Json}
import io.circe.syntax._

case class Ingredient(amount: Option[Float], metric: Option[Metric], name: String)

object Ingredient {
  implicit val encoder: Encoder[Ingredient] = ingredient =>
    Json.obj(
    "amount" -> ingredient.amount.asJson,
      "metric" -> ingredient.metric.asJson,
      "name" -> ingredient.name.asJson
  )
  implicit val ingredientRead: Read[Ingredient] = {
    Read[(Option[Float], Option[Metric], String)].map {
      case (amount, metric, name) => Ingredient(amount, metric, name)
    }
  }

  implicit val ingredientWrite: Write[Ingredient] = {
    Write[(Option[Float], Option[Metric], String)].contramap { ingredient =>
      (ingredient.amount, ingredient.metric, ingredient.name)
    }
  }
}
