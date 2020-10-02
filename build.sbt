import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val akkaVersion      = "2.5.19"
lazy val scalaTestVersion = "3.0.5"

val deps = Seq(
  "com.typesafe.akka" %% "akka-stream"         % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit"        % akkaVersion,
  "org.scalatest"     %% "scalatest"           % scalaTestVersion
)

lazy val root = (project in file("."))
  .settings(
    name := "akka-streams",
    libraryDependencies ++= deps
  )
