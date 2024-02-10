package com.esteban.rockjvm.http

import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.server.Router
import cats.effect.kernel.Async

class JobsRoutes[F[_]: Async] extends Http4sDsl[F]:
  val serviceRoutes = HttpRoutes.of[F]:
    case GET -> Root / "service" =>
      Ok("service")

  val serviceRouter = Router("/jobs" -> serviceRoutes)
