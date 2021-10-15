val scala3Version = "3.0.2"
val catsVersion = "2.6.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "TryScala",
    version := "0.1.0",

    scalaVersion := scala3Version,
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.15.4",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "3.2.8",
    libraryDependencies += "org.typelevel" %% "cats-free" % catsVersion,
    libraryDependencies += "org.typelevel" %% "cats-laws" % catsVersion,
    libraryDependencies += "org.typelevel" %% "cats-mtl" % "1.2.1",

    libraryDependencies += "co.fs2" %% "fs2-core" % "3.1.5",
    libraryDependencies += "co.fs2" %% "fs2-io" % "3.1.5",

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  )
