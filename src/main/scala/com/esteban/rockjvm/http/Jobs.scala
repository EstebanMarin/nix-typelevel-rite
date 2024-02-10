package com.esteban.rockjvm.http

import cats.*
import cats.implicits.*
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.server.Router
import cats.effect.kernel.Async

class JobsRoutes[F[_]: Async] private extends Http4sDsl[F]:
  private val allJobs = HttpRoutes.of[F]:
    case POST -> Root =>
      Ok("TODO")

  private val jobById = HttpRoutes.of[F]:
    case GET -> Root / UUIDVar(id) =>
      Ok(s"TODO: get job by id $id")

  private val allRoute = jobById <+> allJobs

  val serviceRouter = Router("/jobs" -> allRoute)
