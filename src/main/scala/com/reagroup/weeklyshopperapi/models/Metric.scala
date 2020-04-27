package com.reagroup.weeklyshopperapi.models

import io.circe.{Encoder, Json}
import io.circe.syntax._


sealed trait Metric

case object Grams extends Metric
case object Kilograms extends Metric
case object Millilitre extends Metric
case object Litre extends Metric
case object Cup extends Metric
case object TableSpoon extends Metric
case object TeaSpoon extends Metric
case object Other extends Metric

object Metric {
  implicit val encoder: Encoder[Metric] = (m: Metric) => metricToJson(m)

  private def metricToJson(m: Metric): Json = m match {
    case Grams => "g".asJson
    case Kilograms => "kg".asJson
    case Millilitre => "ml".asJson
    case Litre => "l".asJson
    case Cup => "cup".asJson
    case TableSpoon => "tbsp".asJson
    case TeaSpoon => "tsp".asJson
  }
}
