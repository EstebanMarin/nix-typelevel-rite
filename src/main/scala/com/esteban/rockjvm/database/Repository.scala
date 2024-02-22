package com.esteban.rockjvm.database

import cats.*
import cats.effect.*
import cats.effect.kernel.Async
import doobie.*
import doobie.implicits.*
import doobie.util.transactor.Transactor

import com.esteban.rockjvm.model.db.Job.*

trait JobRepository[F[_]]:
  def readinessProve: F[String]

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
        sql"select * from jobs_info".query[JobInfo].to[List].transact(xa)
