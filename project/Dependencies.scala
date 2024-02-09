import sbt._

object Dependencies {
  object iocirce {
    object circe {
      val `circe-core` =
        "io.circe" %% "circe-core" % "0.14.1"
      val `circe-generic` =
        "io.circe" %% "circe-generic" % "0.14.1"
      val `circe-parser` =
        "io.circe" %% "circe-parser" % "0.14.1"
      val `circe-literal` =
        "io.circe" %% "circe-literal" % "0.14.6"
    }
  }
  object is {
    object cir {
      val cirisVersion = "3.5.0"
      val ciris =
        "is.cir" %% "ciris" % cirisVersion
      val `ciris-htt4s` =
        "is.cir" %% "ciris-http4s" % cirisVersion
    }
  }
  object com {
    object eed3si9n {
      object expecty {
        val expecty =
          "com.eed3si9n.expecty" %% "expecty" % "0.16.0"
      }
    }
  }

  object org {
    object scalacheck {
      val scalacheck =
        "org.scalacheck" %% "scalacheck" % "1.17.0"
    }

    object scalameta {
      val munit =
        moduleId("munit")

      val `munit-scalacheck` =
        moduleId("munit-scalacheck")

      private def moduleId(artifact: String): ModuleID =
        "org.scalameta" %% artifact % "1.0.0-M10"
    }

    object typelevel {
      val `discipline-munit` =
        "org.typelevel" %% "discipline-munit" % "1.0.9"
      val `cats-core` =
        "org.typelevel" %% "cats-core" % "2.10.0"
      val `cats-effect` =
        "org.typelevel" %% "cats-effect" % "3.5.3"
      val `munit-cats-effect` =
        "org.typelevel" %% "munit-cats-effect" % "2.0.0-M4"
    }

    object http4s {
      val https4version = "0.23.25"
      val `http4s-dsl` =
        "org.http4s" %% "http4s-dsl" % https4version
      val `http4s-core` =
        "org.http4s" %% "http4s-core" % https4version
      val `htt4s-server` =
        "org.http4s" %% "http4s-server" % https4version
      val `http4s-client` =
        "org.http4s" %% "http4s-client" % https4version
      val `http4s-ember-core` =
        "org.http4s" %% "http4s-ember-core" % https4version
      val `http4s-ember-server` =
        "org.http4s" %% "http4s-ember-server" % https4version
      val `http4s-ember-client` =
        "org.http4s" %% "http4s-ember-client" % https4version
      val `http4s-circe` =
        "org.http4s" %% "http4s-circe" % https4version
    }
  }
}
