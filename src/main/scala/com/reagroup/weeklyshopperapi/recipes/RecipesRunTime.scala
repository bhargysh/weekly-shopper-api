package com.reagroup.weeklyshopperapi.recipes

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.Recipe
import com.reagroup.weeklyshopperapi.recipes.bycategory.{RecipesByCategoryController, RecipesByCategoryRepository}
import doobie.hikari.HikariTransactor

object RecipesRunTime {

  def apply(dbTransactor: HikariTransactor[IO]): RecipesRoutes = {

    val recipesRepository = new RecipesRepository(dbTransactor)

    val recipeController = new RecipesController(recipesRepository.fetchRecipes())

    val recipesByCategoryRepository = new RecipesByCategoryRepository(dbTransactor)

    val recipesByCategoryController = new RecipesByCategoryController(recipesByCategoryRepository.fetchRecipesByCategory)

    new RecipesRoutes(recipeController.handleGetRecipes(), recipesByCategoryController.recipesByCategoryHandler)
  }
}
