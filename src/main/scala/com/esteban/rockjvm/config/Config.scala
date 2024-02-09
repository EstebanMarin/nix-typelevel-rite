package com.esteban.rockjvm.config

import com.comcast.ip4s.{ Port, Host }
import cats.*
import cats.implicits.*
import ciris.*
import ciris.env

final case class ServerConfig(port: Port, host: Host)

trait Config[F[_]: MonadThrow]:
  def server: F[(Port, Host)]

object Config:
  given ConfigDecoder[String, Port] =
    ???

  given ConfigDecoder[String, Host] =
    ???

  def make[F[_]: MonadThrow]: Config[F] = new Config[F]:
    val databaseConfig: ConfigValue[Effect, ServerConfig] =
      (
        env("SERVER_PORT").as[Port].default(Port.fromInt(8080).get),
        env("SERVER_HOST").as[Host].default(Host.fromString("localhost").get),
      ).parMapN(ServerConfig.apply(_, _))

    def server: F[(Port, Host)] =
      MonadThrow[F].catchNonFatal:
        (Port.fromInt(8080).get, Host.fromString("localhost").get)
