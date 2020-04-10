package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe

class RecipesService(fetchRecipes: IO[Vector[Recipe]]) {
  def getRecipes(): IO[Vector[Recipe]] = fetchRecipes
}
