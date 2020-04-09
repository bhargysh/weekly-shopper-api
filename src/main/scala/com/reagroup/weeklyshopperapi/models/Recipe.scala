package com.reagroup.weeklyshopperapi.models

//import java.net.URL
import java.time.Instant

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


//case class Ingredient(measurement: String, rawStr: String)