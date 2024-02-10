package com.esteban.rockjvm.http

import cats.effect.kernel.Async
import cats.syntax.all.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

class AllRoutes[F[_]: Async] extends Http4sDsl[F]:
  val healthRoutes = HealthAPI[F].healthRouter
  val jobsRoutes = JobsRoutes[F].serviceRouter
  val allRoutes = Router("/api" -> (healthRoutes <+> jobsRoutes))
