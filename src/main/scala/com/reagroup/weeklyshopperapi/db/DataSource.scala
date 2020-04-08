package com.reagroup.weeklyshopperapi.db

import cats.effect.{Blocker, ContextShift, IO, Resource}
import com.reagroup.weeklyshopperapi.config.DatabaseConfig
import doobie.hikari.HikariTransactor

import scala.concurrent.ExecutionContext

object DataSource {

  @SuppressWarnings(Array("org.wartremover.warts.ImplicitParameter"))
  def apply(config: DatabaseConfig, connEC: ExecutionContext, blocker: Blocker)(
    implicit cf: ContextShift[IO]
  ): Resource[IO, HikariTransactor[IO]] = {
    HikariTransactor.newHikariTransactor(
      driverClassName = "org.postgresql.Driver",
      url = config.url,
      user = config.userName,
      pass = config.password,
      connEC,
      blocker,
    )
  }
}
