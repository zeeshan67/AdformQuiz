name := "adform-quiz"

version := "0.1"

organization := "com.adform"

scalaVersion := "2.11.5"

resolvers ++= Seq("Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/",
                  "Spray Repository"    at "https://repo.spray.io")

// Assembly settings
mainClass in Global := Some("com.adform.quiz.Main")
