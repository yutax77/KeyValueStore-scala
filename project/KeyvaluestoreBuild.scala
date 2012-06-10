import sbt._
import sbt.Keys._

object KeyvaluestoreBuild extends Build {

  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"
  lazy val dependencies = Seq(
    scalaTest
  )

  lazy val keyvaluestore = Project(
    id = "keyvaluestore",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "KeyValueStore",
      organization := "com.yutax77",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.2",
      // add other settings here
      libraryDependencies ++= dependencies
    )
  )
}
