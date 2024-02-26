package com.esteban.rockjvm.database

import cats.*
import cats.effect.*
import cats.effect.kernel.Async
import doobie.*
import doobie.implicits.*
import doobie.util.transactor.Transactor

import com.esteban.rockjvm.model.db.Job.*
import java.util.UUID

trait JobRepository[F[_]]:
  def readinessProve: F[String]
  def allJobs: F[List[JobInfo]]
  def getJobById(id: UUID): F[Option[JobInfo]]

object JobRepository:
  def make[F[_]: Async]: JobRepository[F] =
    new JobRepository[F]:
      val xa: Transactor[F] = Transactor.fromDriverManager[F](
        driver = "org.postgresql.Driver",
        url = "jdbc:postgresql://localhost:5432/postgres",
        user = "postgres",
        password = "postgres",
        logHandler = None,
      )

      def readinessProve: F[String] =
        sql"select version()".query[String].unique.transact(xa)

      def allJobs: F[List[JobInfo]] =
        sql"select * from job_info".query[JobInfo].to[List].transact(xa)

      given Meta[UUID] = ???
      // {
      //   Some(UUID.fromString())
      //   UUID.fromString match
      //     // case Some(uuid) =>         Meta[String].timap()(uuid.toString)
      //     case Some(uuid) =>         ???
      //     case None => throw new Exception("UUID parsing failed")
      //   }

      // Meta[String].timap()(_.toString)
      // }
      def getJobById(id: UUID): F[Option[JobInfo]] =
        println(s"select * from job_info where id = '${id.toString()}';")
        // println(sql"select * from job_info where id = '${id.toString()};")
        // sql"select * from job_info where id = ${id.toString()}"
        sql"select * from job_info where id = $id"
          // sql"select * from job_info where id = 'd290f1ee-6c54-4b01-90e6-d701748f0851'"
          .query[JobInfo]
          .option
          .transact(xa)
