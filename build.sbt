name := """pagelets"""

lazy val root = (project in file(".")).
  settings(Seq(
    organization := "org.splink",
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test,
      "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
      "org.mockito" % "mockito-core" % "2.27.0" % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.3" % Test,
      "com.typesafe.play" %% "play" % "2.7.1",
      "com.typesafe.akka" %% "akka-stream" % "2.5.22",
      "commons-codec" % "commons-codec" % "1.12"),
      scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-feature",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials")
  ))


author := "Karel Cemus"

github := "KarelCemus/pagelets"

lazy val publishSettings = Seq(
  crossScalaVersions := Seq("2.11.11", scalaVersion.value),
  licenses := Seq("Apache2 License" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
  homepage := Some(url("https://github.com/splink/pagelets")),
  scmInfo := Some(ScmInfo(url("https://github.com/splink/pagelets"), "scm:git:git@github.com:splink/pagelets.git")),
  pomExtra :=
    <developers>
      <developer>
        <id>splink</id>
        <name>Max Kugland</name>
        <url>http://splink.org</url>
      </developer>
    </developers>
)
