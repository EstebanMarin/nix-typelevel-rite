package com.esteban.rockjvm.http

package com.example.http4s.ember

import org.http4s.ember.server.EmberServerBuilder
import cats.effect.*
import cats.*
import cats.syntax.all.*
import fs2.*
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits.*
import org.http4s.server.Server
import _root_.com.esteban.rockjvm.model.ServerConfig

trait HttpService[F[_]] extends Http4sDsl[F]:
  def server: Resource[F, Server]

object HttpService:
  def make[F[_]: Async](ServerConfig: ServerConfig): HttpService[F] = new HttpService[F]:
    val allRoutes = HealthAPI[F].healthRouter <+> JobsRoutes[F].serviceRouter
    val server: Resource[F, Server] = EmberServerBuilder
      .default[F]
      .withHost(ServerConfig.host)
      .withPort(ServerConfig.port)
      .withHttpApp(allRoutes.orNotFound)
      .build
