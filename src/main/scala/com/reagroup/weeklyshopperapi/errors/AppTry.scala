package com.reagroup.weeklyshopperapi.errors

import scala.util.Try

object AppTry {
  def apply[T](r: => T): Either[Throwable, T] =
    Try(r) match {
      case scala.util.Success(t) => Right(t)
      case scala.util.Failure(e) => Left(e)
    }
}
