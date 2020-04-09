package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe

class RecipesService(ioOfRecipes: IO[Vector[Option[Recipe]]]) {
  def convertToRecipes(): Vector[Option[Recipe]] =
    ioOfRecipes.flatMap { recipes =>
      recipes
    } // TODO: fix this
}
