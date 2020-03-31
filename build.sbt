name := "scala-use"
version := "0.1"
scalaVersion := "2.13.1"

// projects

lazy val settings = commonSettings
lazy val commonDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.1.1"
  , "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val global = project
  .in(file("."))
  .settings(settings)
  .disablePlugins(AssemblyPlugin)
  .aggregate(
    common,
    strings,
    numbers
  )

lazy val common = project
  .settings(
    name := "common",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val strings = project
  .settings(
    name := "strings",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(
    )
  )
  .dependsOn(
    common,
    numbers
  )

lazy val numbers = project
  .settings(
    name := "numbers",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(
    )
  )
  .dependsOn(
    common
  )