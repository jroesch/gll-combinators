name := "gll-combinators"

organization := "com.codecommit"

version := "2.2-SNAPSHOT"

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("https://github.com/djspiewak/gll-combinators"))

scalaVersion := "2.10.0"

crossScalaVersions := Seq("2.9.1", "2.9.2", "2.10.0-RC3")
  
logBuffered := false       // gives us incremental output from Specs2
  
publishArtifact in (Compile, packageDoc) := false

publishTo <<= version { v: String =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

resolvers ++= Seq(
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases"
)

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git://github.com/djspiewak/gll-combinators.git</url>
    <connection>scm:git:git://github.com/djspiewak/gll-combinators.git</connection>
  </scm>
  <developers>
    <developer>
      <id>djspiewak</id>
      <name>Daniel Spiewak</name>
      <url>http://www.codecommit.com/blog</url>
    </developer>
  </developers>)
