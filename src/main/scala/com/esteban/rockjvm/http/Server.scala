package com.esteban.rockjvm.http

package com.example.http4s.ember

import _root_.io.circe.*
import _root_.org.http4s.ember.server.EmberServerBuilder
import cats.effect.*
import cats.syntax.all.*
import _root_.com.comcast.ip4s.*
// import com.comcast.ip4s.{ Host as ComHost, Port as ComPort }
// import com.

import fs2.*
import org.http4s.*
import org.http4s.circe.*
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits.*
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.websocket.WebSocketFrame
import org.http4s.server.Server

trait ServerService[F[_]]:
  val dsl: Http4sDsl[F]

  def server: Resource[F, Server]

object ServerService:
  def make[F[_]: Async]: ServerService[F] = new ServerService[F]:
    val dsl = Http4sDsl[F]
    import dsl.*
    val httpApp = HttpRoutes.of[F]:
      case GET -> Root / "hello" =>
        Ok("Hello, world!")
    val server: Resource[F, Server] = EmberServerBuilder
      .default[F]
      .withHttpApp(httpApp.orNotFound)
      .build
