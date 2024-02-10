package com.esteban.rockjvm.http

import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.server.Router
import cats.effect.kernel.Async
import cats.syntax.all.*

class AllRoutes[F[_]: Async] extends Http4sDsl[F]:
  val healthRoutes = HealthAPI[F].healthRouter
  val jobsRoutes = JobsRoutes[F].serviceRouter
  val allRoutes = Router("/api" -> (healthRoutes <+> jobsRoutes))
