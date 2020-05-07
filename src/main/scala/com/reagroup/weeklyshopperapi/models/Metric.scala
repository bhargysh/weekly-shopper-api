package com.reagroup.weeklyshopperapi.models

import doobie.util.meta.Meta
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

object Metric {
  implicit val encoder: Encoder[Metric] = (m: Metric) => metricToJson(m)

  implicit val metricMeta: Meta[Metric] = Meta[String]
    .imap(Metric.convertFromString)(Metric.convertFromMetric)

  private def metricToJson(m: Metric): Json = m match {
    case Grams => "g".asJson
    case Kilograms => "kg".asJson
    case Millilitre => "ml".asJson
    case Litre => "l".asJson
    case Cup => "cup".asJson
    case TableSpoon => "tbsp".asJson
    case TeaSpoon => "tsp".asJson
  }

  private def convertFromString(validStr: String): Metric = validStr match {
    case "g" => Grams
    case "kg" => Kilograms
    case "ml" => Millilitre
    case "l" => Litre
    case "cup" => Cup
    case "tbsp" => TableSpoon
    case "tsp" => TeaSpoon
  }

  private def convertFromMetric(metric: Metric): String = metric match {
    case Grams => "g"
    case Kilograms => "kg"
    case Millilitre => "ml"
    case Litre => "l"
    case Cup => "cup"
    case TableSpoon => "tbsp"
    case TeaSpoon => "tsp"
  }
}
