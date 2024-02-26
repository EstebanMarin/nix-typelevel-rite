package com.esteban.rockjvm.logger

import org.typelevel.log4cats.Logger
import cats.*
import cats.syntax.all.*

object logger:
  extension [F[_], E, A](
    fa: F[A]
  )(using
    me: MonadError[F, E],
    logger: Logger[F],
  )
    def log(sucess: A => String, error: E => String): F[A] = fa.attemptTap:
      case Left(e) => logger.error(error(e))
      case Right(value) => logger.info(sucess(value))

    def logError(error: E => String) = fa.attemptTap:
      case Left(e) => logger.error(error(e))
      case Right(_) => ().pure[F]
