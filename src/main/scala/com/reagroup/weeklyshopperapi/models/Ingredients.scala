package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class Ingredients(value: String)

object Ingredients {
  implicit val ingredientsEncoder: Encoder[Ingredients] = Encoder.encodeString.contramap(_.value)

  implicit val ingredientsDecoder: Decoder[Ingredients] = Decoder.decodeString.map(Ingredients(_))

  implicit val ingredientsMeta: Meta[Ingredients] = Meta[String].imap(Ingredients(_))(_.value)
}