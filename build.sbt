import Dependencies._

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"
ThisBuild / scalaVersion                                   := "2.13.7"

inThisBuild(
  List(
    scalaVersion               := "2.13.7",
    scalafixScalaBinaryVersion := "2.13",
    semanticdbEnabled          := true,
    semanticdbVersion          := scalafixSemanticdb.revision
  )
)

// GitHub publish set-up
githubOwner       := "highlandcows"
githubRepository  := "highlandcows-utils-lib"
githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("GITHUB_TOKEN")

lazy val root = (project in file("."))
  .settings(
    name    := "highlandcows-utils-lib",
    version := Helpers.sysPropOrDefault("version", "0.1.0-SNAPSHOT"),
    scalacOptions ++= Seq("-Wunused", "-deprecation"),
    libraryDependencies ++= Dependencies.all
  )
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
