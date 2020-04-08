package com.reagroup.weeklyshopperapi

import cats.effect.IO
import org.http4s.dsl.io._
import org.http4s.{HttpRoutes}

object AppRoutes {
  val routes: HttpRoutes[IO] = HttpRoutes.of {
    case GET -> Root / "hello" => Ok("hello world")
  }
}
