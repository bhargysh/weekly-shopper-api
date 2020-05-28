package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class InstructionNumber(value: Int) extends AnyVal

object InstructionNumber {
  implicit val stepNumberEncoder: Encoder[InstructionNumber] = Encoder.encodeInt.contramap(_.value)

  implicit val stepNumberDecoder: Decoder[InstructionNumber] = Decoder.decodeInt.map(InstructionNumber(_))

  implicit val stepNumberMeta: Meta[InstructionNumber] = Meta[Int].imap(InstructionNumber(_))(_.value)
}
