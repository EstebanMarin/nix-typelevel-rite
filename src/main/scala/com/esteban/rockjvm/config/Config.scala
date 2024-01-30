package com.esteban.rockjvm.config

import com.comcast.ip4s.{ Port, Host }
import cats.*

trait Config[F[_]: MonadThrow]:
  def server: F[(Port, Host)]

object Config:
  def make[F[_]: MonadThrow]: Config[F] = new Config[F]:
    def server: F[(Port, Host)] =
      MonadThrow[F].catchNonFatal:
        (Port.fromInt(8080).get, Host.fromString("localhost").get)
