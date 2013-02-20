import sbtrelease.ReleasePlugin._

organization := "org.typelevel"

scalaVersion := "2.10.0"

crossScalaVersions := Seq("2.9.2", "2.10.0")

scalacOptions <++= (scalaVersion) map { sv =>
  if (sv.contains("2.10"))
    Seq("-feature", "-deprecation", "-language:implicitConversions", "-language:higherKinds", "-language:existentials")
  else
    Seq("-Ydependent-method-types", "-deprecation")
}

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.0-M8"

resolvers += Resolver.sonatypeRepo("releases")

publishTo <<= (version).apply { v =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("Snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("Releases" at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(
  Option(System.getProperty("build.publish.credentials")) map (new File(_)) getOrElse (Path.userHome / ".ivy2" / ".credentials")
)

pomIncludeRepository := Function.const(false)

pomExtra := (
  <url>http://typelevel.org/scalaz</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/folone/scalaz-geo</url>
    <connection>scm:git:git://github.com/folone/scalaz-geo.git</connection>
    <developerConnection>scm:git:git@github.com:folone/scalaz-geo.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>larsrh</id>
      <name>Lars Hupel</name>
      <url>https://github.com/larsrh</url>
    </developer>
    <developer>
      <id>folone</id>
      <name>George Leontiev</name>
      <url>https://github.com/folone</url>
    </developer>
  </developers>
)

// vim: expandtab:ts=2:sw=2