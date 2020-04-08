package com.reagroup.weeklyshopperapi

package object errors {
  type ErrorOr[+A] = Either[AppError, A]
}
