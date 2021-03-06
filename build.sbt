name := "irc-announcer"

version := "0.0.1-SNAPHOST"

scalaVersion := "2.10.2"

resolvers ++= Seq(
	"mtgto repos" at "http://scala-irc-bot.github.com/scala-irc-bot/maven/",
	"Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
	"Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies := Seq(
  "net.mtgto" %% "scala-irc-bot" % "0.2.1",
  "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
  "org.slf4j" % "slf4j-api" % "1.7.2"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")

mainClass in (Compile, run) := Some("net.mtgto.irc.DefaultClient")