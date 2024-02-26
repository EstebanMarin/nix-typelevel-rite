package com.esteban.rockjvm.http

import _root_.com.esteban.rockjvm.database.JobRepository
import _root_.com.esteban.rockjvm.model.db.Job.*
import cats.*
import cats.effect.kernel.Async
import cats.implicits.*
import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import org.http4s.*
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder
import org.http4s.circe.*
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

class JobsRoutes[F[_]: Async: JobRepository: MonadThrow] extends Http4sDsl[F]:
  given Codec[JobInfo] =
    deriveCodec[JobInfo]
  given EntityEncoder[F, List[JobInfo]] =
    jsonEncoderOf[F, List[JobInfo]]

  given EntityEncoder[F, Option[JobInfo]] =
    jsonEncoderOf[F, Option[JobInfo]]

  private val allJobs = HttpRoutes.of[F]:
    case GET -> Root / "get-all" =>
      Ok(summon[JobRepository[F]].allJobs)

  private val jobById = HttpRoutes.of[F]:
    case GET -> Root / UUIDVar(id) =>
      Ok(for repo <- summon[JobRepository[F]].getJobById(id) yield repo match
        case Some(job) => job
        case None => throw new Exception(s"Job with id $id not found")
      )

  val allRoutes = jobById <+> allJobs

  val serviceRouter = Router("/jobs" -> allRoutes)
