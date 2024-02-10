package com.esteban.rockjvm.model

import ciris.ConfigDecoder
import com.comcast.ip4s.*

final case class ServerConfig(port: Port, host: Host)

object ServerConfig:
  given ConfigDecoder[String, Port] =
    ConfigDecoder[String].mapOption("Port")(Port.fromString)

  given ConfigDecoder[String, Host] =
    ConfigDecoder[String].mapOption("Host")(Host.fromString)
