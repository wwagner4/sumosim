import sbt._
import Keys._
import sbt.Package.ManifestAttributes
import sbtassembly.Plugin._
import com.typesafe.sbteclipse.plugin.EclipsePlugin._

import net.virtualvoid.sbt.graph.Plugin._

import com.typesafe.sbteclipse.core.Validation
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseTransformerFactory

import scala.scalajs.sbtplugin._
import ScalaJSPlugin._
import ScalaJSKeys._

object SumosimBuild extends Build {

  // Constant values
  object D {

    val version = "1.0-SNAPSHOT"

    val scalaMainVersion = "2.10"
    val scalaVersion = s"$scalaMainVersion.4"
    val doctusVersion = "1.0.3-SNAPSHOT"

  }

  // Settings
  object S {

    lazy val commonSettings =
      graphSettings ++
        Seq(
          version := D.version,
          scalaVersion := D.scalaVersion,
          organization := "net.entelijan.sumosim2",
          publishMavenStyle := true,
          publishTo := Some("entelijan-repo" at "http://entelijan.net/artifactory/libs-release-local/"),
          credentials += Credentials("Artifactory Realm", "entelijan.net", "deploy", "deploy"),
          resolvers += "entelijan" at "http://entelijan.net/artifactory/repo/",
          EclipseKeys.withSource := true)

    lazy val sumosimSettings =
      commonSettings ++
        scalaJSBuildSettings  ++
        Seq(
          libraryDependencies ++= Seq(
            "net.entelijan" %%% "doctus-core" % D.doctusVersion,
            "org.scalatest" % s"scalatest_${D.scalaMainVersion}" % "2.1.0"))

    lazy val sumosimSwingSettings =
      commonSettings ++
        Seq(
          fork := true,
          libraryDependencies ++= Seq(
            "net.entelijan" %% "doctus-swing" % D.doctusVersion))

    lazy val sumosimScalajsSettings =
      sumosimSettings ++
        scalaJSBuildSettings  ++
        Seq(
          libraryDependencies += "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
          libraryDependencies += "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
          libraryDependencies += "net.entelijan" %%% "doctus-scalajs" % D.doctusVersion)

  }

  // Project definitions
  lazy val root = Project(id = "sumosim-pom",
    base = file("."), //
    settings = S.sumosimSettings).aggregate(
      sumosim,
      sumosimSwing,
      sumosimScalajs)

  lazy val sumosim = Project(
    id = "sumosim",
    base = file("sumosim"), // 
    settings = S.sumosimSettings)

  lazy val sumosimSwing = Project(
    id = "sumosim-swing",
    base = file("sumosim-swing"),
    settings = S.sumosimSwingSettings //
    ).dependsOn(sumosim)

  lazy val sumosimScalajs = Project(
    id = "sumosim-scalajs",
    base = file("sumosim-scalajs"),
    settings = S.sumosimScalajsSettings //
    ).dependsOn(sumosim)

}

