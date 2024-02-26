package com.esteban.rockjvm.http

import _root_.com.esteban.rockjvm.database.JobRepository
import cats.effect.kernel.Async
import cats.syntax.all.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.typelevel.log4cats.Logger

class AllRoutes[F[_]: Async: JobRepository: Logger] extends Http4sDsl[F]:
  val healthRoutes = HealthAPI[F].healthRouter
  val jobsRoutes = JobsRoutes[F].serviceRouter
  val allRoutes = Router("/api" -> (healthRoutes <+> jobsRoutes))
