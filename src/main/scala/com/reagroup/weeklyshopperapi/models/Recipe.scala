package com.reagroup.weeklyshopperapi.models

//import java.net.URL
import java.time.Instant

case class Recipe(id: RecipeId,
                  recipeType: RecipeType,
                  recipeName: String,
                  ingredients: String, // Vector[Ingredient]
                  instructions: String,
                  duration: Int,
                  recipeLink: String, // URL
                  imageLink: String, // URL
                  createdAt: Instant,
                  servings: Int)

case class RecipeId(int: Int)
case class Ingredient(measurement: String, rawStr: String)