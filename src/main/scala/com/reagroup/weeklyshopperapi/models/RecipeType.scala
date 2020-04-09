package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta

sealed trait RecipeType

case object Breakfast extends RecipeType
case object Lunch extends RecipeType
case object Dinner extends RecipeType
case object Snack extends RecipeType

object RecipeType {

  def convertFromInt(int: Int): RecipeType = int match {
    case 1 => Breakfast
    case 2 => Lunch
    case 3 => Dinner
    case 4 => Snack
  }

  def convertFromType(recipeType: RecipeType): Int = recipeType match {
    case Breakfast => 1
    case Lunch => 2
    case Dinner => 3
    case Snack => 4
  }

  implicit val recipeTypeMeta: Meta[RecipeType] = Meta[Int]
    .imap(RecipeType.convertFromInt)(RecipeType.convertFromType)
}
