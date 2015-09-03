name := "scala-freemarker"

organization := "com.queue"

isSnapshot := true

version := "0.0.5"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers ++= Seq(
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.0.3",
  "org.freemarker" % "freemarker" % "2.3.23",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "commons-beanutils" % "commons-beanutils" % "1.8.0",
  "org.apache.commons" % "commons-lang3" % "3.1" % "test"
)

publishMavenStyle := true

publishTo := {
  val dropsource = "http://repo.dropsource.biz:8081/"
  if (isSnapshot.value)
    Some("snapshots" at dropsource + "artifactory/scala-snapshots")
  else
    Some("releases" at dropsource + "artifactory/scala-releases")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")