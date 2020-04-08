package com.reagroup.weeklyshopperapi

import cats.effect.IO
import com.reagroup.propertyinsights.testing.apis.Http4sSpecHelpers.{body, request}
import org.http4s.Status
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.specs2.matcher.{IOMatchers, RunTimedMatchers}
import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

class AppRoutesSpec extends Specification with RunTimedMatchers[IO] with IOMatchers {
  "AppRoutes" should {
    val appRoutes = AppRoutes

    val endpoints = List(
      request[IO](path = "/hello", method = GET) -> "hello world"
    )

    Fragment.foreach(endpoints) { endpoint =>
      val (req, expectedResponse) = endpoint

      s"for ${req.method} ${req.uri}" in {

        "return correct status" in {
          appRoutes.routes.orNotFound(req).map(_.status) must returnValue(Status.Ok)
        }

        "return expected response" in {
          body(appRoutes.routes.orNotFound(req)) must beEqualTo(expectedResponse)
        }
      }
    }
  }
}
