import sbt._

object Dependencies {
  lazy val all =
    Seq("org.scalatest" %% "scalatest" % "3.2.9" % Test, "ch.qos.logback" % "logback-classic" % "1.2.10" % Test)
}
