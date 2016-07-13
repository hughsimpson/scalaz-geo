organization := "silasmariner"

name := "scalaz-geo"

version := "0.3.0"

scalaVersion := "2.11.8"

bintrayOrganization := Some("silasmariner")

bintrayOmitLicense := true

publishMavenStyle := true

scalacOptions <++= (scalaVersion) map { sv =>
  val versionDepOpts =
    if (sv startsWith "2.9")
      Seq("-Ydependent-method-types", "-deprecation")
    else
      // does not contain -deprecation (because of ClassManifest)
      // contains -language:postfixOps (because 1+ as a parameter to a higher-order function is treated as a postfix op)
      Seq("-feature", "-language:implicitConversions", "-language:higherKinds", "-language:existentials", "-language:postfixOps")
  Seq("-unchecked") ++ versionDepOpts
}

resolvers += Resolver.sonatypeRepo("releases")

resolvers += Resolver.jcenterRepo

libraryDependencies <++= (scalaVersion) { sv =>
  val scalazSpecsVersion = if(sv startsWith "2.9") "0.1.5" else "0.2"
  Seq(
    "org.scalaz"    %% "scalaz-core"   % "7.2.4",
    "org.specs2"    %% "specs2-core"   % "3.7.2" % "test",
    "org.typelevel" %% "scalaz-specs2" % "0.3.0" % "test"
  )
}