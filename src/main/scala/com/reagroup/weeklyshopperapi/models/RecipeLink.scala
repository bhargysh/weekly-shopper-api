package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class RecipeLink(value: String)

object RecipeLink {
  implicit val recipeLinkEncoder: Encoder[RecipeLink] = Encoder.encodeString.contramap(_.value)

  implicit val recipeLinkDecoder: Decoder[RecipeLink] = Decoder.decodeString.map(RecipeLink(_))

  implicit val recipeLinkMeta: Meta[RecipeLink] = Meta[String].imap(RecipeLink(_))(_.value)
}