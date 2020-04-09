package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta

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

  def convertFromType(maybeRecipeType: Option[RecipeType]): Int = maybeRecipeType match {
    case Some(Breakfast) => 1
    case Some(Lunch) => 2
    case Some(Dinner) => 3
    case Some(Snack) => 4
    case None => 0 // TODO: don't know what to do here 
  }

  implicit val recipeTypeMeta: Meta[RecipeType] = Meta[Int]
    .imap(RecipeType.convertFromInt)(RecipeType.convertFromType)
//    .imap()

}
