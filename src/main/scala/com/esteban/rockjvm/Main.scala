package com.esteban
package rockjvm

import cats.*
import cats.implicits.*
import cats.effect.*
import io.circe.*
import io.circe.syntax.*
import io.circe.parser.*
import io.circe.generic.semiauto.*
import org.http4s.circe.*
import org.http4s.headers.*
import org.http4s.*
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.implicits.*
import org.http4s.server.*
import org.http4s.ember.server.EmberServerBuilder
import java.util.UUID
import org.http4s.dsl.io.*
import org.typelevel.ci.CIString
import com.comcast.ip4s.{ Host as ComHost, Port as ComPort }
import com.esteban.rockjvm.config.Config
import fs2.compression.DeflateParams.Level.FIVE

/*
 1 - add health endpoint
 2 - add minimal configuration
 3 - add minimal logging
 4 - basic http server layout
 */

object MainApp extends IOApp.Simple:
  // def healthEndpoint[F[_]: Monad] = HttpRoutes.of[F]:
  //   val dsl = Http4sDsl[F]
  //   import dsl.*

  //   case GET -> Root / "health" =>
  //     Ok("I'm alive!")

  // val httpApp = healthEndpoint[IO].orNotFound
  def run: IO[Unit] =
    IO.println("Hello, world!")
