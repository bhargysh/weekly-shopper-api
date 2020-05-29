package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

case class RecipeImageLink(value: String)

object RecipeImageLink {
  implicit val RecipeImageLinkEncoder: Encoder[RecipeImageLink] = Encoder.encodeString.contramap(_.value)

  implicit val RecipeImageLinkDecoder: Decoder[RecipeImageLink] = Decoder.decodeString.map(RecipeImageLink(_))

  implicit val RecipeImageLinkMeta: Meta[RecipeImageLink] = Meta[String].imap(RecipeImageLink(_))(_.value)
}
