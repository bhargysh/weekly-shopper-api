package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}

final case class InstructionDescription(value: String) extends AnyVal

object InstructionDescription {
  implicit val ingredientDescriptionEncoder: Encoder[InstructionDescription] = Encoder.encodeString.contramap(_.value)

  implicit val ingredientDescriptionDecoder: Decoder[InstructionDescription] = Decoder.decodeString.map(InstructionDescription(_))

  implicit val ingredientDescriptionMeta: Meta[InstructionDescription] = Meta[String].imap(InstructionDescription(_))(_.value)
}