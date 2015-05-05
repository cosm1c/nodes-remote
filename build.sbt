import com.typesafe.sbt.SbtNativePackager.packageArchetype

name := """nodes-remote"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

// TODO: Use enablePlugins(JavaServerAppPackaging)
packageArchetype.java_server

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.10",
  "com.typesafe.akka" %% "akka-remote" % "2.3.10",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.10" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")

scalacOptions in ThisBuild ++= Seq(
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "-deprecation", // warning and location for usages of deprecated APIs
  "-feature", // warning and location for usages of features that should be imported explicitly
  "-unchecked", // additional warnings where generated code depends on assumptions
  "-Xlint", // recommended additional warnings
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code"
)

maintainer in Docker := "Cory Prowse <cory@prowse.com>"

dockerBaseImage in Docker := "dockerfile/java:oracle-java8"

dockerExposedPorts in Docker := Seq(2553)

packageSummary in Docker := "Remote Node"

packageDescription := "Spiking technology"
