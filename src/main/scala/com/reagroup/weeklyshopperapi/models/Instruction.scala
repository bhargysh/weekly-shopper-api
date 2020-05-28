package com.reagroup.weeklyshopperapi.models

import io.circe.{Encoder, Json}
import io.circe.syntax._

final case class Instruction(instructionNumber: InstructionNumber, description: InstructionDescription)

object Instruction {
  implicit val encoder: Encoder[Instruction] = instruction =>
    Json.obj(
      "stepNumber" -> instruction.instructionNumber.asJson,
      "stepDescription" -> instruction.description.asJson
    )
}
