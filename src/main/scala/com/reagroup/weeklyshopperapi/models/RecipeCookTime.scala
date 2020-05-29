package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class RecipeCookTime(value: Int)

object RecipeCookTime {
  implicit val cookTimeEncoder: Encoder[RecipeCookTime] = Encoder.encodeInt.contramap(_.value)

  implicit val cookTimeDecoder: Decoder[RecipeCookTime] = Decoder.decodeInt.map(RecipeCookTime(_))

  implicit val cookTimeMeta: Meta[RecipeCookTime] = Meta[Int].imap(RecipeCookTime(_))(_.value)
}