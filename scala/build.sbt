lazy val root = (project in file(".")).
  settings(
    name := "lab1",
    version := "1.0",
    scalaVersion := "2.11.6"
  )

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.2.10"
)
