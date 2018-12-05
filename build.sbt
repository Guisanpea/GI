name := "GI"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.181-R13",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "com.typesafe.slick" %% "slick-codegen" % "3.2.3",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "6.2.1.jre8"
)