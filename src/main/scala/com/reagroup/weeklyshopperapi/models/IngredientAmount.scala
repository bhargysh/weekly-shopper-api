package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

case class IngredientAmount(value: Float)

object IngredientAmount {
  implicit val ingredientAmountEncoder: Encoder[IngredientAmount] = Encoder.encodeFloat.contramap(_.value)

  implicit val ingredientAmountDecoder: Decoder[IngredientAmount] = Decoder.decodeFloat.map(IngredientAmount(_))

  implicit val ingredientAmountMeta: Meta[IngredientAmount] = Meta[Float].imap(IngredientAmount(_))(_.value)
}
