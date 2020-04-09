package com.reagroup.weeklyshopperapi.models

//import java.net.URL
import java.time.Instant
import doobie.util.meta.Meta

case class Recipe(id: RecipeId,
                  recipeType: Option[RecipeType],
                  recipeName: String,
                  ingredients: String, // Vector[Ingredient]
                  instructions: String,
                  duration: Int,
                  recipeLink: Option[String], // URL
                  imageLink: Option[String], // URL
                  createdAt: Instant,
                  servings: Int)

final case class RecipeId(int: Int) extends AnyVal

object RecipeId {
  implicit val recipeIdMeta: Meta[RecipeId] = Meta[Int].timap(RecipeId(_))(_.int)
}
//case class Ingredient(measurement: String, rawStr: String)