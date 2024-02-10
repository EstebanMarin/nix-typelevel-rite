package com.esteban
package rockjvm

import cats.*
import cats.effect.*
import com.esteban.rockjvm.http.com.example.http4s.ember.HttpService
import com.esteban.rockjvm.config.Config

/*
 1 - add health endpoint
 2 - add minimal configuration
 3 - add minimal logging
 4 - basic http server layout
 */

object MainApp extends IOApp.Simple:
  def run: IO[Unit] =
    for
      config <- Config.make[IO].serviceConfig
      _ <- IO.println(s"Starting server on port ${config.port}")
      server <- HttpService.make[IO](config).server.use(_ => IO.never)
    yield server
