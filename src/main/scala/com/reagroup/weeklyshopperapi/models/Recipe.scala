package com.reagroup.weeklyshopperapi.models

import java.time.OffsetDateTime

import doobie.util.{Read, Write}
import doobie.util.meta.Meta
import io.circe.{parser, Encoder, Json}
import io.circe.syntax._

final case class Recipe(
  id: RecipeId,
  category: RecipeCategory,
  name: RecipeName,
  ingredients: Json,
  instructions: Json,
  duration: RecipeCookTime,
  link: Option[RecipeLink],
  imageLink: Option[RecipeImageLink],
  createdAt: Option[OffsetDateTime],
  servings: Servings
)

object Recipe {
  implicit val encoder: Encoder[Recipe] = recipe =>
    Json.obj(
      "id" -> recipe.id.asJson,
      "category" -> recipe.category.asJson,
      "name" -> recipe.name.asJson,
      "ingredients" -> recipe.ingredients.asJson,
      "instructions" -> recipe.instructions.asJson,
      "duration" -> recipe.duration.asJson,
      "link" -> recipe.link.asJson,
      "imageLink" -> recipe.imageLink.asJson,
      "createdAt" -> recipe.createdAt.asJson,
      "servings" -> recipe.servings.asJson
    )

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  implicit val circeJsonMeta: Meta[Json] =
    Meta[String].imap(strJson => parser.parse(strJson).fold[Json](throw _, identity))(_.noSpaces)

  implicit val instantMeta: Meta[OffsetDateTime] = doobie.implicits.javatime.JavaOffsetDateTimeMeta

  implicit val propertyAttributesQueryRowRead: Read[Recipe] = {
    Read[
      (
        RecipeId,
        RecipeCategory,
        RecipeName,
        Json,
        Json,
        RecipeCookTime,
        Option[RecipeLink],
        Option[RecipeImageLink],
        Option[OffsetDateTime],
        Servings
      )
    ].map {
      case (id, category, name, ingredients, instructions, duration, link, imageLink, createdAt, servings) =>
        Recipe(id, category, name, ingredients, instructions, duration, link, imageLink, createdAt, servings)
    }
  }

  implicit val propertyAttributesQueryRowWrite: Write[Recipe] = {
    Write[
      (
        RecipeId,
        RecipeCategory,
        RecipeName,
        Json,
        Json,
        RecipeCookTime,
        Option[RecipeLink],
        Option[RecipeImageLink],
        Option[OffsetDateTime],
        Servings
      )
    ].contramap { recipe =>
      (
        recipe.id,
        recipe.category,
        recipe.name,
        recipe.ingredients,
        recipe.instructions,
        recipe.duration,
        recipe.link,
        recipe.imageLink,
        recipe.createdAt,
        recipe.servings
      )
    }
  }
}
