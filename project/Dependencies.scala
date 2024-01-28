import sbt._

object Dependencies {
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
    }

    object http4s {
      val `http4s-dsl` =
        "org.http4s" %% "http4s-dsl" % "0.23.25"
      val `http4s-core` =
        "org.http4s" %% "http4s-core" % "0.23.25"
    }
  }
}
