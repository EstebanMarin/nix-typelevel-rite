package com.esteban
package rockjvm

import cats.*
import cats.effect.*

import com.esteban.rockjvm.config.Config
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.database.JobRepository
import com.esteban.rockjvm.model.ServerConfig
import fs2.Stream

object MainApp extends IOApp.Simple:
  val configStream: Stream[IO, ServerConfig] =
    Stream.bracket(Config.make[IO].serviceConfig)(_ => IO.unit)
  val dbStream: Stream[IO, JobRepository[IO]] =
    Stream.bracket(IO(JobRepository.make[IO]))(_ => IO.unit)
  def serverStream(config: ServerConfig) =
    Stream.bracket(IO(HttpService.make[IO](config).server.useForever))(_ => IO.unit)

  val appScafolding =
    configStream.map(identity).compile.drain

  val test =
    for
      conf: ServerConfig <- configStream
      server <- serverStream(conf)
    yield ()
  def run: IO[Unit] =
    for
      config: ServerConfig <- Config.make[IO].serviceConfig
      jobRepo <- JobRepository.make[IO].readinessProve
      server <-
        IO.println(s"${jobRepo}") *>
          HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
