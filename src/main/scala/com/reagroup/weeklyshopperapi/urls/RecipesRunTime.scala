package com.reagroup.weeklyshopperapi.urls

import cats.effect.IO
import com.reagroup.weeklyshopperapi.urls.bycategory._
import com.reagroup.weeklyshopperapi.urls.recipes.{RecipesController, RecipesRepository}
import doobie.hikari.HikariTransactor

object RecipesRunTime {

  def apply(dbTransactor: HikariTransactor[IO]): RecipesRoutes = {

    val recipesRepository = new RecipesRepository(dbTransactor)

    val recipeController = new RecipesController(recipesRepository.fetchRecipes())

    val recipesByCategoryRepository = new RecipesByCategoryRepository(dbTransactor)

    val recipesByCategoryController = new RecipesByCategoryController(recipesByCategoryRepository.fetchRecipes)

    new RecipesRoutes(recipeController.fetchAll(), recipesByCategoryController.fetchRecipes, ???)
  }
}
