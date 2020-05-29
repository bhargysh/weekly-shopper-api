package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class Instructions(value: String)

object Instructions {
  implicit val instructionsEncoder: Encoder[Instructions] = Encoder.encodeString.contramap(_.value)

  implicit val instructionsDecoder: Decoder[Instructions] = Decoder.decodeString.map(Instructions(_))

  implicit val instructionsMeta: Meta[Instructions] = Meta[String].imap(Instructions(_))(_.value)
}