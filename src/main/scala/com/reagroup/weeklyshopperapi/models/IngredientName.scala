package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class IngredientName(value: String) extends AnyVal

object IngredientName {
  implicit val ingredientNameEncoder: Encoder[IngredientName] = Encoder.encodeString.contramap(_.value)

  implicit val ingredientNameDecoder: Decoder[IngredientName] = Decoder.decodeString.map(IngredientName(_))

  implicit val ingredientNameMeta: Meta[IngredientName] = Meta[String].imap(IngredientName(_))(_.value)
}
