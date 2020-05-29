package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

case class Servings(value: Int)

object Servings {
  implicit val servingsEncoder: Encoder[Servings] = Encoder.encodeInt.contramap(_.value)

  implicit val servingsDecoder: Decoder[Servings] = Decoder.decodeInt.map(Servings(_))

  implicit val servingsMeta: Meta[Servings] = Meta[Int].imap(Servings(_))(_.value)
}
