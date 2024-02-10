package com.esteban
package rockjvm

import cats.*
import cats.effect.*
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.config.Config

// Create readiness prove that test DB connection is working

object MainApp extends IOApp.Simple:
  def run: IO[Unit] =
    for
      config <- Config.make[IO].serviceConfig
      server <- HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
