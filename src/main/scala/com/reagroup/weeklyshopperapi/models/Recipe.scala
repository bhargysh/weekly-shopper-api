package com.reagroup.weeklyshopperapi.models

import java.time.Instant

import io.circe.{Encoder, Json}
import io.circe.syntax._

case class Recipe(id: RecipeId,
                  category: Option[RecipeType],
                  name: String,
                  ingredients: String, // Vector[Ingredient] -> Ingredient(measurement: Measurement, rawIngredient: String)
                  instructions: String, // Vector[Instruction] -> Instruction(step: Int, direction: String)
                  duration: Int, //could be minutes or hours and minutes 🤔
                  link: Option[String], // URL
                  imageLink: Option[String], // URL
                  createdAt: Instant,
                  servings: Int)


object Recipe {
  implicit val encoder: Encoder[Recipe] = recipe => Json.obj(
    "id" -> recipe.id.asJson,
    "category" -> recipe.category.asJson,
    "name" -> recipe.name.asJson,
    "ingredients" -> recipe.ingredients.asJson,
    "instructions" -> recipe.instructions.asJson,
    "duration" -> recipe.duration.asJson,
    "link" -> recipe.link.asJson,
    "imageLink" -> recipe.imageLink.asJson,
    "createdAt" -> recipe.createdAt.asJson,
    "servings" -> recipe.servings.asJson,
  )
}