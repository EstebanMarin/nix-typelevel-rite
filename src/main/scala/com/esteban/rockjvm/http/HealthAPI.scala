package com.esteban.rockjvm.http

import cats.*
import cats.implicits.*
import cats.effect.kernel.Async
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

class HealthAPI[F[_]: Async] extends Http4sDsl[F]:
  val heathAPI = HttpRoutes.of[F]:
    case GET -> Root / "ping" =>
      Ok("pong")
  val dbReadiness = HttpRoutes.of[F]:
    case GET -> Root / "postgres" =>
      Ok("ready")
  val healthRoutes = heathAPI <+> dbReadiness
  val healthRouter = Router("/health" -> healthRoutes)
