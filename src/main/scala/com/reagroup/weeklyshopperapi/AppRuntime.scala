package com.reagroup.weeklyshopperapi

import cats.effect.{ContextShift, IO, Timer}
import cats.implicits._
import com.reagroup.infrastructure.http4s.logging.LoggingMiddleware
import com.reagroup.infrastructure.http4s.middleware.{ErrorHandlingMiddleware, TransactionIdMiddleware}
import com.reagroup.weeklyshopperapi.config.Config
import org.http4s.server.middleware.GZip
import org.http4s.{HttpRoutes, Response}

@SuppressWarnings(Array("org.wartremover.warts.ImplicitParameter"))
class AppRuntime(config: Config)(implicit ecf: ContextShift[IO], timer: Timer[IO]) {

  private val diagnosticChecks = Vector.empty

  private val diagnosticRoutes: HttpRoutes[IO] = new ApplicationDiagnosticRoutes(config, diagnosticChecks).routes()

  private val appServices = AppRoutes.routes <+> diagnosticRoutes

  private val allServices: HttpRoutes[IO] = GZip(appServices, isZippable = (_: Response[IO]) => true)

  val routes: HttpRoutes[IO] = TransactionIdMiddleware(
    LoggingMiddleware(
      ErrorHandlingMiddleware(allServices)
    )
  )

}
