package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
import io.circe.{Decoder, Encoder}
import io.circe.syntax._

final case class RecipeId(rawInt: Int) extends AnyVal

object RecipeId {
  implicit val recipeIdMeta: Meta[RecipeId] = Meta[Int].imap(RecipeId(_))(_.rawInt)

  implicit val encoder: Encoder[RecipeId] = Encoder(id => id.rawInt.asJson)

  implicit val decoder: Decoder[RecipeId] = Decoder.forProduct1("recipeId")(RecipeId(_))
}
