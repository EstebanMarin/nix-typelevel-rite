package com.esteban
package rockjvm

import cats.*
import cats.effect.*

import com.esteban.rockjvm.config.Config
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.database.JobRepository
import com.esteban.rockjvm.model.ServerConfig
import fs2.Stream
// import fs2.io.file.{ Files, Path }

// Create readiness prove that test DB connection is working

object MainApp extends IOApp.Simple:
  val configStream: Stream[IO, ServerConfig] =
    Stream.bracket(Config.make[IO].serviceConfig)(_ => IO.unit)
  val dbStream: Stream[IO, JobRepository[IO]] =
    Stream.bracket(IO(JobRepository.make[IO]))(_ => IO.unit)
  val serverStream = Stream.bracket(IO(HttpService.make[IO]))(_ => IO.unit)

  val appScafolding =
    configStream.map(identity).compile.drain

  val test =
    for conf: ServerConfig <- configStream
    yield ()
  def run: IO[Unit] =
    for
      config: ServerConfig <- Config.make[IO].serviceConfig
      jobRepo <- JobRepository.make[IO].readinessProve
      server <-
        IO.println(s"${jobRepo}") *>
          HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
  // configStream.flatMap((x: Config[IO]) => x.serviceConfig.flatMap() )
  // configStream
  //   .map(config => config.serviceConfig.flatMap(x => dbStream.zipAll(serverStream)))
  //   .compile
  //   .drain

  // for
  //   config: Config[IO] <- configStream
  //   // test <- dbStream.zip(serverStream).flatMap((db, server) => db.readinessPro
  //   test <- Stream.eval(
  //     dbStream.zip(serverStream).flatMap((db, server) => Stream(db.readinessProve))
  //   )
  // yield test
  // configStream
  //   .zip(dbStream)
  //   .zip(serverStream)
  //   .map { case ((config, jobRepo), server) =>
  //     server.server.use(_ => IO.never)
  //   }
  //   .compile
  //   .drain
