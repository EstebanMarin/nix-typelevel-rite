package com.esteban.rockjvm.database

import cats.effect.*
import cats.effect.kernel.Async
import cats.effect.kernel.syntax.async
import cats.implicits.*
import cats.syntax.*
import cats.syntax.all.*
import doobie.*
import doobie.implicits.*
import doobie.util.transactor.Transactor

import com.esteban.rockjvm.model.db.*

trait JobRepository[F[_]]:
  def findAllJobs: F[List[Job]]

object JobRepository:
  def make[F[_]: Async]: JobRepository[F] = ???

//   def make[F[_]: Async]: JobRepository[F] = new JobRepository[F]:
//     val xa: Transactor[F] = Transactor.fromDriverManager[F](
//       driver = "org.postgresql.Driver",
//       url = "jdbc:postgresql://localhost:5432/postgres",
//       user = "postgres",
//       password = "postgres",
//       logHandler = None,
//     )

//     def findAllJobs[F[_]]: F[List[Job]] = ???
