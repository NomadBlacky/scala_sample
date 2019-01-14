val scalikejdbcVersion = "3.3.2"

lazy val TableOfContents = config("tableOfContents").extend(Test)

lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  wartremoverWarnings ++= Warts.unsafe,
  wartremoverWarnings -= Wart.NonUnitStatements
)

lazy val reporter = (project in file("reporter"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5"
    )
  )

lazy val root = (project in file("."))
  .dependsOn(reporter)
  .settings(commonSettings)
  .configs(TableOfContents)
  .settings(inConfig(TableOfContents)(Defaults.testTasks): _*)
  .settings(
    name := "scala_samples",
    version := "1.0",
    TableOfContents / testOptions ++= Seq(
      Tests.Argument(
        TestFrameworks.ScalaTest,
        "-C",
        "org.nomadblacky.scala.reporter.TableOfContentsReporter"
      )
    ),
    libraryDependencies ++= Seq(
      "org.scalactic"        %% "scalactic"              % "3.0.5",
      "org.scalatest"        %% "scalatest"              % "3.0.5" % "test",
      "org.scalacheck"       %% "scalacheck"             % "1.14.0" % "test",
      "com.github.scopt"     %% "scopt"                  % "3.7.1",
      "org.pegdown"          % "pegdown"                 % "1.6.0",
      "org.scala-lang"       % "scala-reflect"           % scalaVersion.value,
      "org.jfree"            % "jfreechart"              % "1.5.0",
      "com.github.pathikrit" %% "better-files"           % "3.7.0",
      "org.scalaz"           %% "scalaz-core"            % "7.2.27",
      "com.typesafe.akka"    %% "akka-http-core"         % "10.1.7",
      "com.typesafe.akka"    %% "akka-stream"            % "2.5.19",
      "com.chuusai"          %% "shapeless"              % "2.3.3",
      "org.typelevel"        %% "cats-core"              % "1.5.0",
      "com.lihaoyi"          %% "ammonite-ops"           % "1.6.0",
      "com.typesafe.play"    %% "play-ahc-ws-standalone" % "1.1.12",
      "org.scalikejdbc"      %% "scalikejdbc"            % scalikejdbcVersion,
      "org.scalikejdbc"      %% "scalikejdbc-config"     % scalikejdbcVersion,
      "org.scalikejdbc"      %% "scalikejdbc-test"       % scalikejdbcVersion % "test",
      "org.skinny-framework" %% "skinny-orm"             % "3.0.1",
      "com.h2database"       % "h2"                      % "1.4.197",
      "ch.qos.logback"       % "logback-classic"         % "1.2.3"
    )
  )

// FIXME: Cannot apply this...
// https://github.com/scalatest/scalatest/commit/10b38d73e804546aaf3690e6496b65d984f2459f#diff-a2caa30f41e1c2f5fac0195d465701cf
// ThisBuild / envVars += "SCALACTIC_FILL_FILE_PATHNAMES" -> "yes"
