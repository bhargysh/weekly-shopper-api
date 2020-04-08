package com.reagroup.weeklyshopperapi

import cats.effect.{ContextShift, IO, Timer}
import com.reagroup.api.infrastructure.diagnostics.{DiagnosticCheckDefinition, DiagnosticConfig}
import com.reagroup.infrastructure.http4s.diagnostics.DiagnosticRoutes
import com.reagroup.weeklyshopperapi.config.Config
import org.http4s.HttpRoutes

@SuppressWarnings(Array("org.wartremover.warts.ImplicitParameter"))
class ApplicationDiagnosticRoutes(
  config: Config,
  diagnosticChecks: Vector[DiagnosticCheckDefinition[IO]]
)(implicit cf: ContextShift[IO], timer: Timer[IO]) {

  def routes(): HttpRoutes[IO] = {
    val configuration = DiagnosticConfig(
      version = config.version,
      heartbeatChecks = Vector.empty,
      diagnosticChecks = diagnosticChecks
    )

    val standardDiagnosticRoutes = new DiagnosticRoutes(
      diagnosticConfig = configuration,
      additionalDiagnosticRoutes = Vector.empty,
      additionalDiagnosticChecks = Map.empty,
      appConfig = Some(config)
    )
    standardDiagnosticRoutes.routes(prefix = "diagnostic")
  }
}
