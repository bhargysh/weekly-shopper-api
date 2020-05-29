package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class RecipeName(value: String) extends AnyVal

object RecipeName {
  implicit val recipeNameEncoder: Encoder[RecipeName] = Encoder.encodeString.contramap(_.value)

  implicit val recipeNameDecoder: Decoder[RecipeName] = Decoder.decodeString.map(RecipeName(_))

  implicit val recipeNameMeta: Meta[RecipeName] = Meta[String].imap(RecipeName(_))(_.value)
}
