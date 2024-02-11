package com.esteban.rockjvm.http

import _root_.com.esteban.rockjvm.database.JobRepository
import cats.*
import cats.effect.kernel.Async
import cats.implicits.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

class JobsRoutes[F[_]: Async: JobRepository] extends Http4sDsl[F]:
  private val allJobs = HttpRoutes.of[F]:
    case POST -> Root =>
      Ok("TODO")

  private val jobById = HttpRoutes.of[F]:
    case GET -> Root / UUIDVar(id) =>
      Ok(s"TODO: $id")

  private val allRoute = jobById <+> allJobs

  val serviceRouter = Router("/jobs" -> allRoute)
