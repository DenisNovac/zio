inThisBuild(
  List(
    scalaVersion := "3.0.0"
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-zio",
    version := "0.1.0",
    libraryDependencies += "dev.zio" %% "zio" % "1.0.9"
  )
