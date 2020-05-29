package com.reagroup.weeklyshopperapi.urls

import cats.effect.IO
import com.reagroup.weeklyshopperapi.urls.bycategory._
import com.reagroup.weeklyshopperapi.urls.recipes.{RecipesController, RecipesRepository}
import com.reagroup.weeklyshopperapi.urls.saverecipe.{SaveRecipeController, SaveRecipeRepository, SaveRecipeService}
import doobie.hikari.HikariTransactor

object RecipesRunTime {

  def apply(dbTransactor: HikariTransactor[IO]): RecipesRoutes = {

    val recipesController: RecipesController = {
      val recipesRepository = new RecipesRepository(dbTransactor)
      new RecipesController(recipesRepository.fetchRecipes())
    }

    val recipesByCategoryController: RecipesByCategoryController = {
      val recipesByCategoryRepository = new RecipesByCategoryRepository(dbTransactor)
      new RecipesByCategoryController(recipesByCategoryRepository.fetchRecipes)
    }

    val saveRecipeController: SaveRecipeController = {
      val saveRecipeRepository = new SaveRecipeRepository(dbTransactor)
      val saveRecipeService = new SaveRecipeService(saveRecipeRepository.saveRecipe)
      new SaveRecipeController(saveRecipeService.save)
    }

    val recipeRoutes = new RecipesRoutes(
      recipesController.fetchAll,
      recipesByCategoryController.fetchRecipes,
      saveRecipeController.save
    )

    recipeRoutes
  }
}
