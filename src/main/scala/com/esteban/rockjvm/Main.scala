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

/*
 1 - add health endpoint
 2 - add minimal configuration
 3 - add minimal logging
 4 - basic http server layout
 */

@main def Main(args: String*): Unit =
  println("─" * 100)

  println("hello world")

  println("─" * 100)
