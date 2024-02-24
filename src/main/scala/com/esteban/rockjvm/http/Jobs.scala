package com.esteban.rockjvm.http

import _root_.com.esteban.rockjvm.database.JobRepository
import cats.*
import cats.effect.kernel.Async
import cats.implicits.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import _root_.com.esteban.rockjvm.model.db.Job.*
import io.circe.Codec
import org.http4s.EntityEncoder
import io.circe.generic.semiauto.deriveCodec
import org.http4s.circe.*
class JobsRoutes[F[_]: Async: JobRepository] extends Http4sDsl[F]:
  given Codec[JobInfo] =
    deriveCodec[JobInfo]
  given EntityEncoder[F, List[JobInfo]] =
    jsonEncoderOf[F, List[JobInfo]]

  private val allJobs = HttpRoutes.of[F]:
    case GET -> Root / "get-all" =>
      Ok(summon[JobRepository[F]].allJobs)

  private val jobById = HttpRoutes.of[F]:
    case GET -> Root / UUIDVar(id) =>
      Ok("TODO")

  val allRoutes = jobById <+> allJobs

  val serviceRouter = Router("/jobs" -> allRoutes)
