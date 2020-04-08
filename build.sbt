name := "weekly-shopper-api"

version := "1.0"

scalaVersion := "2.13.1"

// We force the timezone to UTC to be consistent between deving on host and within docker
// To set this parameter we need to enable forking to a separate JVM process
fork := true

javaOptions += "-Duser.timezone=UTC"

// Overrides the "mainclass" setting in the "Compile" configuration
mainClass in Compile := Some("com.reagroup.weeklyshopperapi.Main") //Used in Universal packageBin

// Overrides the "mainClass" setting in the "Compile" configuration, only during the "run" task
mainClass in (Compile, run) := Some(
  "com.reagroup.weeklyshopperapi.Dev"
) //Used from normal sbt

resolvers += "mvnrepository" at "https://mvnrepository.com/artifact/"
resolvers ++= Seq(
  "rea nexus release" at "https://rea-sonatype-nexus.services.delivery.realestate.com.au/nexus/content/repositories/releases"
)

val catsVersion = "2.1.1"
val catsEffectVersion = "2.1.2"
val circeVersion = "0.13.0"
val http4sVersion = "0.21.1"
val specs2Version = "4.8.3"
val scalaJsonLoggingVersion = "1.4.1"
val piHttp4sMiddlewareVersion = "1.0.0"
val piScalaTestUtil = "1.0.2"
val newRelicVersion = "5.2.0"

libraryDependencies ++= Seq(
  "org.typelevel"           %% "cats-core"                    % catsVersion,
  "org.typelevel"           %% "cats-effect"                  % catsEffectVersion,
  "io.circe"                %% "circe-core"                   % circeVersion,
  "io.circe"                %% "circe-generic"                % circeVersion,
  "io.circe"                %% "circe-literal"                % circeVersion,
  "io.circe"                %% "circe-parser"                 % circeVersion,
  "org.http4s"              %% "http4s-blaze-server"          % http4sVersion,
  "org.http4s"              %% "http4s-circe"                 % http4sVersion,
  "org.http4s"              %% "http4s-dsl"                   % http4sVersion,
  "com.rea-group"           %% "scala-json-logging"           % scalaJsonLoggingVersion,
  "com.rea-group"           %% "pi-http4s-middleware"         % piHttp4sMiddlewareVersion,
  "com.rea-group"           %% "pi-scala-test-util-apis"      % piScalaTestUtil           % "test",
  "org.http4s"              %% "http4s-testing"               % http4sVersion             % "test",
  "org.specs2"              %% "specs2-core"                  % specs2Version             % "test",
  "org.specs2"              %% "specs2-matcher-extra"         % specs2Version             % "test",
  "org.specs2"              %% "specs2-scalacheck"            % specs2Version             % "test"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Xfatal-warnings",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-language:higherKinds",
  "-language:postfixOps",
  "-Ywarn-unused:imports"
)

scalacOptions in Test ++= Seq("-Yrangepos")

scalacOptions in Test --= Seq("-Ywarn-value-discard", "-Ywarn-numeric-widen")

//Allows doing: > testOnly *ExampleSpec -- -ex "this is example two"
testFrameworks := Seq(TestFrameworks.Specs2)

test in assembly := {}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _ @_*) => MergeStrategy.discard
  case _                           => MergeStrategy.first
}

assemblyOutputPath in assembly := new File("target/app.jar")
