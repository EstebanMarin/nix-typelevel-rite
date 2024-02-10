package com.esteban
package rockjvm

import cats.*
import cats.effect.*

import com.esteban.rockjvm.config.Config
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.database.JobRepository
import com.esteban.rockjvm.model.ServerConfig

// Create readiness prove that test DB connection is working

object MainApp extends IOApp.Simple:
  def run: IO[Unit] =
    for
      config: ServerConfig <- Config.make[IO].serviceConfig
      jobRepo <- JobRepository.make[IO].readinessProve
      server <-
        IO.println(s"${jobRepo}") *>
          HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
