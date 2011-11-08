name := "lift-json-either"

version := "0.1"

scalaVersion := "2.9.1"

resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-json" % "2.4-M4",
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")
