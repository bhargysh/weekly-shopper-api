package com.reagroup.weeklyshopperapi.models

import io.circe.{Encoder, Json}
import io.circe.syntax._

case class Instruction(stepNumber: Int, stepDescription: String)

object Instruction {
  implicit val encoder: Encoder[Instruction] = instruction =>
    Json.obj(
      "stepNumber" -> instruction.stepNumber.asJson,
      "stepDescription" -> instruction.stepDescription.asJson
    )
}
