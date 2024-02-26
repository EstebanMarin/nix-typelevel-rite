package com.esteban
package rockjvm

import cats.*
import cats.effect.*

import com.esteban.rockjvm.config.Config
import com.esteban.rockjvm.database.JobRepository
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.model.ServerConfig
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

object MainApp extends IOApp.Simple:
  def run: IO[Unit] =
    for
      config: ServerConfig <- Config.make[IO].serviceConfig
      given JobRepository[IO] = JobRepository.make[IO]
      given Logger[IO] = Slf4jLogger.getLogger[IO]
      server <- HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
