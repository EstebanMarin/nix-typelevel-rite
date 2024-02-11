package com.esteban.rockjvm.http

import _root_.com.esteban.rockjvm.database.JobRepository
import cats.*
import cats.effect.kernel.Async
import cats.implicits.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

class HealthAPI[F[_]: Async: JobRepository] extends Http4sDsl[F]:
  val heathAPI = HttpRoutes.of[F]:
    case GET -> Root / "ping" =>
      Ok("pong")
  val dbReadiness = HttpRoutes.of[F]:
    case GET -> Root / "postgres" =>
      Ok(
        for version <- summon[JobRepository[F]].readinessProve
        yield version
      )
  val healthRoutes = heathAPI <+> dbReadiness
  val healthRouter = Router("/health" -> healthRoutes)
