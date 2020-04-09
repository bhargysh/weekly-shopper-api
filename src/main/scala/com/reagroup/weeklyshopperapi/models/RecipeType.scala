package com.reagroup.weeklyshopperapi.models

sealed trait RecipeType

case object Breakfast extends RecipeType
case object Lunch extends RecipeType
case object Dinner extends RecipeType
case object Snack extends RecipeType

object RecipeType {

  def convertFromInt(int: Int): Option[RecipeType] = int match {
    case 1 => Some(Breakfast)
    case 2 => Some(Lunch)
    case 3 => Some(Dinner)
    case 4 => Some(Snack)
    case _ => None
  }

}