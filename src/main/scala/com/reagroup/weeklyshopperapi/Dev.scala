package com.reagroup.weeklyshopperapi

import cats.effect.{ExitCode, IO, IOApp}
import com.reagroup.weeklyshopperapi.config.Development

object Dev extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    Main.execute(Development)
  }
}
