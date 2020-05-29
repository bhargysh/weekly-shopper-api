package com.reagroup.weeklyshopperapi.urls.saverecipe

import cats.data.Validated.{Invalid, Valid}
import cats.data.ValidatedNel
import cats.effect.IO
import com.reagroup.weeklyshopperapi.models.RecipeId
import com.reagroup.weeklyshopperapi.urls.ErrorHandler
import org.http4s.{Request, Response}
import org.http4s.circe.CirceEntityCodec._
import io.circe.syntax._
import org.http4s.dsl.Http4sDsl

class SaveRecipeController(saveNewRecipe: NewRecipeRequest => IO[ValidatedNel[RecipeValidationError, RecipeId]]) extends Http4sDsl[IO] {
  def save(req: Request[IO]): IO[Response[IO]] = for {
    newRecipeRequest <- req.as[NewRecipeRequest]
    errorOrRecipe <- saveNewRecipe(newRecipeRequest).attempt
    response <- errorOrRecipe match {
      case Left(e) => ErrorHandler(e)
      case Right(Valid(recipeId)) => Created(recipeId)
      case Right(Invalid(validationErrors)) => BadRequest(validationErrors.asJson)
    }
  } yield response
}
