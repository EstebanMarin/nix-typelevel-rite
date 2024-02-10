package com.esteban
package rockjvm

import cats.*
import cats.effect.*

import com.esteban.rockjvm.config.Config
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.database.JobRepository
import com.esteban.rockjvm.model.ServerConfig
import fs2.{ Stream, text }
// import fs2.io.file.{ Files, Path }

// Create readiness prove that test DB connection is working

object MainApp extends IOApp.Simple:
  def run: IO[Unit] =
    val configStream: Stream[IO, Config[IO]] =
      Stream.bracket(IO(Config.make[IO]))(_ => IO.unit)
    val dbStream: Stream[IO, JobRepository[IO]] =
      Stream.bracket(IO(JobRepository.make[IO]))(_ => IO.unit)
    ???

    for
      config: ServerConfig <- Config.make[IO].serviceConfig
      jobRepo <- JobRepository.make[IO].readinessProve
      server <-
        IO.println(s"${jobRepo}") *>
          HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
