package com.esteban
package rockjvm

import cats.*
import cats.effect.*
import com.esteban.rockjvm.http.com.example.http4s.ember.ServerService

/*
 1 - add health endpoint
 2 - add minimal configuration
 3 - add minimal logging
 4 - basic http server layout
 */

object MainApp extends IOApp.Simple:
  def run: IO[Unit] =
    ServerService.make[IO].server.use(_ => IO.never)
