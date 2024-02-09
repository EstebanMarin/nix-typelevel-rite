package com.esteban.rockjvm.config

import com.comcast.ip4s.{ Port, Host }
import cats.*
import cats.implicits.*
import ciris.*
import ciris.env
import cats.effect.kernel.Async
import com.esteban.rockjvm.model.ServerConfig
import com.esteban.rockjvm.model.ServerConfig.given

trait Config[F[_]: MonadThrow]:
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
