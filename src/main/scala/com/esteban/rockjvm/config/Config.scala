package com.esteban.rockjvm.config

import cats.*
import cats.effect.kernel.Async
import cats.implicits.*
import ciris.*
import com.comcast.ip4s.*

import com.esteban.rockjvm.model.ServerConfig
import com.esteban.rockjvm.model.ServerConfig.given

trait Config[F[_]: Async]:
  def serviceConfig: F[ServerConfig]

object Config:
  def make[F[_]: Async]: Config[F] = new Config[F]:
    val databaseConfig: ConfigValue[Effect, ServerConfig] =
      (
        env("SERVER_PORT").as[Port].default(Port.fromInt(8080).get),
        env("SERVER_HOST").as[Host].default(Host.fromString("localhost").get),
      ).parMapN(ServerConfig.apply(_, _))

    def serviceConfig: F[ServerConfig] =
      databaseConfig.load[F]
