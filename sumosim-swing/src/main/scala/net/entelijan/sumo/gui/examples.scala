package net.entelijan.sumo.gui

import doctus.swing._
import net.entelijan.sumo.gui.example.{CleverVsForwardBackwardExample, ManualVsForwardBackwardExample, ManualVsStandstillExample, RotatingVsForwardBackwardExample}
import net.entelijan.sumo.robot._
import net.entelijan.sumo.sound.SoundDesign00

import scala.swing._
import scala.swing.event._

object RotatingVsForwardBackwardApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing
  val ex = new RotatingVsForwardBackwardExample(c, Some(sd), sl)
  new SumoApp(ex, p)
}

object CleverVsForwardBackwardApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing
  val ex = new CleverVsForwardBackwardExample(c, Some(sd), sl)
  new SumoApp(ex, p)
}

object ManualVsForwardBackwardApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val d = {
    val up = DoctusActivatableSwingKey(p, Key.Up)
    val down = DoctusActivatableSwingKey(p, Key.Down)
    val left = DoctusActivatableSwingKey(p, Key.Left)
    val right = DoctusActivatableSwingKey(p, Key.Right)
    new UpDownLeftRight(
      ControlerValue(up, DoctusSchedulerSwing),
      ControlerValue(down, DoctusSchedulerSwing),
      ControlerValue(left, DoctusSchedulerSwing),
      ControlerValue(right, DoctusSchedulerSwing))
  }
  val ex = new ManualVsForwardBackwardExample(c, d, Some(sd), sl)
  new SumoApp(ex, p)
}

object ManualVsStandstillApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val d = {
    val up = DoctusActivatableSwingKey(p, Key.Up)
    val down = DoctusActivatableSwingKey(p, Key.Down)
    val left = DoctusActivatableSwingKey(p, Key.Left)
    val right = DoctusActivatableSwingKey(p, Key.Right)
    new UpDownLeftRight(
      ControlerValue(up, DoctusSchedulerSwing),
      ControlerValue(down, DoctusSchedulerSwing),
      ControlerValue(left, DoctusSchedulerSwing),
      ControlerValue(right, DoctusSchedulerSwing))
  }
  val ex = new ManualVsStandstillExample(c, d, Some(sd), sl)
  new SumoApp(ex, p)
}

object ManualVsForwardBackwardButtonsApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val bup = new Label { text = "UP" }
  val bdown = new Label { text = "DOWN" }
  val bleft = new Label { text = "LEFT" }
  val bright = new Label  {text = "RIGHT" }

  val d = {
    val up = DoctusActivatableSwing(bup)
    val down = DoctusActivatableSwing(bdown)
    val left = DoctusActivatableSwing(bleft)
    val right = DoctusActivatableSwing(bright)
    new UpDownLeftRight(
      ControlerValue(up, DoctusSchedulerSwing),
      ControlerValue(down, DoctusSchedulerSwing),
      ControlerValue(left, DoctusSchedulerSwing),
      ControlerValue(right, DoctusSchedulerSwing))
  }
  val ex = new ManualVsForwardBackwardExample(c, d, Some(sd), sl)
  new SumoButtonsApp(ex, p, bup, bdown, bleft, bright)
}

object ManualVsStandstillButtonsApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val bup = new Label { text = "UP" }
  val bdown = new Label { text = "DOWN" }
  val bleft = new Label { text = "LEFT" }
  val bright = new Label { text = "RIGHT" }

  val d = {
    val up = DoctusActivatableSwing(bup)
    val down = DoctusActivatableSwing(bdown)
    val left = DoctusActivatableSwing(bleft)
    val right = DoctusActivatableSwing(bright)
    new UpDownLeftRight(
      ControlerValue(up, DoctusSchedulerSwing),
      ControlerValue(down, DoctusSchedulerSwing),
      ControlerValue(left, DoctusSchedulerSwing),
      ControlerValue(right, DoctusSchedulerSwing))
  }
  val ex = new ManualVsStandstillExample(c, d, Some(sd), sl)
  new SumoButtonsApp(ex, p, bup, bdown, bleft, bright)
}

