package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import doobie.hikari.HikariTransactor

object RecipesRunTime {

  def apply(dbTransactor: HikariTransactor[IO]): RecipesRoutes = {
//    val recipeRepository = new RecipesRepository()
//
//    val recipeService = new RecipesService()
//
//    val recipeController = new RecipesController()
    new RecipesRoutes()
  }
}
