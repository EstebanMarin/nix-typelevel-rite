import Dependencies._

ThisBuild / organization := "com.esteban"
ThisBuild / scalaVersion := "3.3.1"

lazy val `rockjvm` =
  project
    .in(file("."))
    .settings(name := "rockjvm")
    .settings(commonSettings)
    .settings(autoImportSettings)
    .settings(dependencies)

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions := {
      (Compile / console / scalacOptions)
        .value
        .filterNot(_.contains("wartremover"))
        .filterNot(Scalac.Lint.toSet)
        .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
    },
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
    // cs launch scalac:3.3.1 -- -Wconf:help
    // src is not yet available for Scala3
    // scalacOptions += s"-Wconf:src=${target.value}/.*:s",
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val autoImportSettings = Seq(
  scalacOptions +=
    Seq(
      "java.lang",
      "scala",
      "scala.Predef",
      "scala.annotation",
      "scala.util.chaining",
    ).mkString(start = "-Yimports:", sep = ",", end = ""),
  Test / scalacOptions +=
    Seq(
      "org.scalacheck",
      "org.scalacheck.Prop",
    ).mkString(start = "-Yimports:", sep = ",", end = ""),
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    org.typelevel.`cats-core`,
    org.typelevel.`cats-effect`,
    org.http4s.`http4s-dsl`,
    org.http4s.`http4s-core`,
    org.http4s.`http4s-circe`,
    org.http4s.`http4s-client`,
    org.http4s.`htt4s-server`,
    org.http4s.`http4s-ember-core`,
    org.http4s.`http4s-ember-server`,
    org.http4s.`http4s-ember-client`,
    is.cir.ciris,
    is.cir.`ciris-htt4s`,
    iocirce.circe.`circe-core`,
    iocirce.circe.`circe-generic`,
    iocirce.circe.`circe-parser`,
    iocirce.circe.`circe-literal`,
    org.typolecat.`doobie-core`,
    org.typolecat.`doobie-postgres`,
    co.fs2.`fs2-core`,
    co.fs2.`fs2-io`,
  ),
  libraryDependencies ++= Seq(
    com.eed3si9n.expecty.expecty,
    org.scalacheck.scalacheck,
    org.scalameta.`munit-scalacheck`,
    org.scalameta.munit,
    org.typelevel.`discipline-munit`,
    org.typelevel.`munit-cats-effect`,
  ).map(_ % Test),
)
