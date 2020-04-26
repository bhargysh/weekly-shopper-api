package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}
import io.circe.syntax._

sealed trait RecipeCategory

case object Breakfast extends RecipeCategory
case object Lunch extends RecipeCategory
case object Dinner extends RecipeCategory
case object Snack extends RecipeCategory

object RecipeCategory {

  val convertFromInt: PartialFunction[Int, RecipeCategory] = {
    case 1 => Breakfast
    case 2 => Lunch
    case 3 => Dinner
    case 4 => Snack
  }

  def convertFromType(recipeType: RecipeCategory): Int = recipeType match {
    case Breakfast => 1
    case Lunch => 2
    case Dinner => 3
    case Snack => 4
  }

  implicit val recipeTypeMeta: Meta[RecipeCategory] = Meta[Int]
    .imap(RecipeCategory.convertFromInt)(RecipeCategory.convertFromType)

  implicit val encoder: Encoder[RecipeCategory] = Encoder {
    case Breakfast => "Breakfast".asJson
    case Lunch => "Lunch".asJson
    case Dinner => "Dinner".asJson
    case Snack => "Snack".asJson
  }

  implicit val decoder: Decoder[RecipeCategory] = Decoder
    .forProduct1("category")(RecipeCategory.convertFromInt)
}
