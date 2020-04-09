package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta

case class RecipeId(int: Int) extends AnyVal

object RecipeId {
  implicit val recipeIdMeta: Meta[RecipeId] = Meta[Int].imap(RecipeId(_))(_.int)
}
