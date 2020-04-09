package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import doobie.hikari.HikariTransactor

object RecipesRunTime {

  def apply(dbTransactor: HikariTransactor[IO]): RecipesRoutes = {
    val recipeRepository = new RecipesRepository(dbTransactor)
    val ioOfRecipes = recipeRepository.fetchRecipes()

    val recipeService = new RecipesService(ioOfRecipes)

//    val recipeController = new RecipesController()
    new RecipesRoutes()
  }
}
