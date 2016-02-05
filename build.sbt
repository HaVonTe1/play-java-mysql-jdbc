name := """play-java-mysql-jdbc"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.34"
libraryDependencies += javaJdbc

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

enablePlugins(SbtNativePackager)


fork in run := false

fork in Test := false