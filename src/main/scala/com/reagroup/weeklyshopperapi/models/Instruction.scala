package com.reagroup.weeklyshopperapi.models

import doobie.util.{Read, Write}
import io.circe.{Encoder, Json}
import io.circe.syntax._

case class Instruction(stepNumber: Int, stepDescription: String)

object Instruction {
  implicit val encoder: Encoder[Instruction] = instruction =>
    Json.obj(
      "stepNumber" -> instruction.stepNumber.asJson,
      "stepDescription" -> instruction.stepDescription.asJson
    )

  implicit val instructionRead: Read[Instruction] = {
    Read[(Int, String)].map {
      case (stepNumber, stepInstruction) => Instruction(stepNumber, stepInstruction)
    }
  }

  implicit val instructionWrite: Write[Instruction] = {
    Write[(Int, String)].contramap { instruction =>
      (instruction.stepNumber, instruction.stepDescription)
    }
  }
}
