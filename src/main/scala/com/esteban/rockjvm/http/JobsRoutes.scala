package com.esteban.rockjvm.http

import _root_.com.esteban.rockjvm.database.JobRepository
import _root_.com.esteban.rockjvm.model.db.Job.*
import cats.*
import cats.syntax.all.*
import cats.effect.kernel.Async
import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import org.http4s.*
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder
import org.http4s.circe.*
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.typelevel.log4cats.Logger

class JobsRoutes[F[_]: Async: JobRepository: MonadThrow: Logger] extends Http4sDsl[F]:
  given Codec[JobInfo] =
    deriveCodec[JobInfo]
  given EntityEncoder[F, List[JobInfo]] =
    jsonEncoderOf[F, List[JobInfo]]

  given EntityEncoder[F, Option[JobInfo]] =
    jsonEncoderOf[F, Option[JobInfo]]

  import _root_.com.esteban.rockjvm.logging.syntax.*

  private val allJobs = HttpRoutes.of[F]:
    case GET -> Root / "get-all" =>
      for
        _ <- Logger[F].info("Getting all jobs")
        allJobs <- summon[JobRepository[F]].allJobs
        resp <- Ok(allJobs)
      yield resp

  private val jobById = HttpRoutes.of[F]:
    case GET -> Root / UUIDVar(id) =>
      for
        _ <- Logger[F].info(s"Getting job with id $id")
        job <-
          summon[JobRepository[F]]
            .getJobById(id)
            .logError(e => s"ID $id not found: ${e.getMessage}")
        res <- job match
          case Some(job) => Ok(job)
          case None => NotFound(s"Job with id $id not found")
      yield res

  val allRoutes = jobById <+> allJobs

  val serviceRouter = Router("/jobs" -> allRoutes)
