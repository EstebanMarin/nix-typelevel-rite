package com.esteban.rockjvm.model

import com.comcast.ip4s.Port
import com.comcast.ip4s.Host
import ciris.ConfigDecoder

final case class ServerConfig(port: Port, host: Host)

object ServerConfig:
  given ConfigDecoder[String, Port] =
    ConfigDecoder[String].mapOption("Port")(Port.fromString)

  given ConfigDecoder[String, Host] =
    ConfigDecoder[String].mapOption("Host")(Host.fromString)
