package com.reagroup.weeklyshopperapi.urls

import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.RecipeCategory
import com.reagroup.weeklyshopperapi.urls.RecipesRoutes.RecipeCategoryQueryParamMatcher
import org.http4s.dsl.io._
import org.http4s.{HttpRoutes, ParseFailure, QueryParamDecoder, QueryParameterValue, Request, Response}
import cats.implicits._
import cats.data.{Validated, ValidatedNel}

import scala.util.{Failure, Success, Try}

class RecipesRoutes(recipesHandler: IO[Response[IO]],
                    recipesByCategoryHandler: RecipeCategory => IO[Response[IO]],
                    saveRecipeHandler: Request[IO] => IO[Response[IO]]
                   ) {
  private val RecipesRoute = Root / "recipes"

  implicit val openRoutes: HttpRoutes[IO] = HttpRoutes.of {
    case GET -> RecipesRoute => recipesHandler
    case GET -> RecipesRoute / "by-category" :? RecipeCategoryQueryParamMatcher(category) =>
      handleRecipesByCategoryRequest(category)
    case req @ POST -> RecipesRoute => saveRecipeHandler(req)
  }

  private def handleRecipesByCategoryRequest(
    validatedCategory: ValidatedNel[ParseFailure, RecipeCategory]
  ): IO[Response[IO]] = validatedCategory match {
    case Validated.Valid(category) => recipesByCategoryHandler(category)
    case Validated.Invalid(errors) => BadRequest(errors.toList.map(_.sanitized).mkString("\n"))
  }
}

object RecipesRoutes {

  implicit val recipeCategoryQueryParamDecoder: QueryParamDecoder[RecipeCategory] = { param: QueryParameterValue =>
    Try(RecipeCategory.convertFromInt(param.value.toInt)) match {
      case Failure(exception) => ParseFailure("Invalid Recipe Category", exception.getMessage).invalidNel
      case Success(value) => value.validNel
    }
  }
  object RecipeCategoryQueryParamMatcher extends ValidatingQueryParamDecoderMatcher[RecipeCategory]("category")
}
