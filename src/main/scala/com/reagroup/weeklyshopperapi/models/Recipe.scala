package com.reagroup.weeklyshopperapi.models

import io.circe.{Encoder, Json}
import io.circe.syntax._

final case class Recipe(
                         id: RecipeId,
                         category: RecipeCategory,
                         name: String,
                         ingredients: List[Ingredient],
//                         instructions: List[Instruction],
//                         duration: Int,
//                         link: Option[String],
//                         imageLink: Option[String],
//                         createdAt: Option[OffsetDateTime],
//                         servings: Int
)

object Recipe {
  implicit val encoder: Encoder[Recipe] = recipe =>
    Json.obj(
      "id" -> recipe.id.asJson,
      "category" -> recipe.category.asJson,
      "name" -> recipe.name.asJson,
      "ingredients" -> recipe.ingredients.asJson,
//      "instructions" -> recipe.instructions.asJson,
//      "duration" -> recipe.duration.asJson,
//      "link" -> recipe.link.asJson,
//      "imageLink" -> recipe.imageLink.asJson,
//      "createdAt" -> recipe.createdAt.asJson,
//      "servings" -> recipe.servings.asJson
    )
//  implicit val instantMeta: Meta[OffsetDateTime] = doobie.implicits.javatime.JavaOffsetDateTimeMeta
}
