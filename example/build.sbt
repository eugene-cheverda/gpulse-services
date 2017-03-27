import sbt._, Keys._
import dependencies._

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
  _test(finch.test),
  _test(scalatest.core)
)

console.settings