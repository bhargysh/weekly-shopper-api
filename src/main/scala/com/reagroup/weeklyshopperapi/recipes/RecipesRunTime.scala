package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import doobie.hikari.HikariTransactor

object RecipesRunTime {

  def apply(dbTransactor: HikariTransactor[IO]): RecipesRoutes = {

    val recipeRepository = new RecipesRepository(dbTransactor)
    val ioOfRecipes: IO[Vector[Recipe]] = recipeRepository.fetchRecipes()

    val recipeService = new RecipesService(ioOfRecipes)

    val recipeController = new RecipesController(recipeService.getRecipes())

    new RecipesRoutes(recipeController.handleGetRecipes())
  }
}
