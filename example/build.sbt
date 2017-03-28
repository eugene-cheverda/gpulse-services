import sbt._, Keys._
import dependencies._
import com.typesafe.sbt.packager.docker._
import com.typesafe.sbt.packager.docker.Dockerfile._

libraryDependencies ++= Seq (
  cats.all,
  fs2.core,
  finch.core,
  finch.circe,
  logback.core,
  logger.core,
  finagle.core,
  finagle.server,
  finch.core,
  finch.circe,
  circe.core,
  circe.generic,
  scopt.core,
  avro.core,
  typesafe.config,
  _test(finch.test),
  _test(scalatest.core)
)

console.settings

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case "BUILD" => MergeStrategy.discard
  case _ => MergeStrategy.deduplicate
}

// Remove all jar mappings in universal and append the fat jar
mappings in Universal := {
  val universalMappings = (mappings in Universal).value
  val fatJar = (assembly in Compile).value
  val filtered = universalMappings.filter {
    case (file, name) => !name.endsWith(".jar")
  }
  filtered :+ (fatJar -> ("lib/" + fatJar.getName))
}

enablePlugins(DockerPlugin, JavaAppPackaging)
dockerRepository := Some("pulse")

dockerCommands := Seq(
  Cmd("FROM", "openjdk:8"),
  Cmd("COPY", "opt/docker/lib/*.jar", "/example-assembly-0.1-SNAPSHOT.jar"),
  ExecCmd("CMD", "java", "-jar",
    "-Djava.rmi.server.hostname=example-app",
    "-Dcom.sun.management.jmxremote",
    "-Dcom.sun.management.jmxremote.port=4000",
    "-Dcom.sun.management.jmxremote.rmi.port=4000",
    "-Dcom.sun.management.jmxremote.local.only=false",
    "-Dcom.sun.management.jmxremote.authenticate=false",
    "-Dcom.sun.management.jmxremote.ssl=false",
    "/example-assembly-0.1-SNAPSHOT.jar")
)

