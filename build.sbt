name := "akkahttp-quickstart"

version := "0.1"

scalaVersion := "2.12.11"

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.13",
    "com.typesafe.akka" %% "akka-testkit"  % "2.5.13" % Test,
    "com.typesafe.akka" %% "akka-stream"  % "2.5.13",
    "com.typesafe.akka" %% "akka-stream-testkit"  % "2.5.13" % Test,
    "com.typesafe.akka" %% "akka-http"  % "10.1.3",
    "com.typesafe.akka" %% "akka-http-testkit"  % "10.1.3" % Test,
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "de.heikoseeberger" %% "akka-http-circe" % "1.31.0",
    "org.scalatest" %% "scalatest" % "3.1.2" % Test
)