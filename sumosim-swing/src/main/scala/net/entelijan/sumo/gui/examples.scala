package net.entelijan.sumo.gui

import doctus.swing._
import net.entelijan.sumo.gui.example._
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

object MultiApp extends App {
  // Create components
  val p = new DoctusPanel
  val ba = new Button("A")
  val bb = new Button("B")
  val bc = new Button("C")
  val bd = new Button("D")

  // Layout components
  val panel = new BorderPanel {
    add(p, BorderPanel.Position.Center)
    val bp = new FlowPanel(ba, bb, bc, bd)
    add(bp, BorderPanel.Position.South)
  }

  // Wrap components with DoctusClasses
  val c = new DoctusCanvasSwing(p)
  val sl = DoctusSchedulerSwing
  val d = {
    val up = DoctusActivatableSwingKey(panel, Key.Up)
    val down = DoctusActivatableSwingKey(panel, Key.Down)
    val left = DoctusActivatableSwingKey(panel, Key.Left)
    val right = DoctusActivatableSwingKey(panel, Key.Right)
    UpDownLeftRight(up, down, left, right, sl)
  }

  // Start the controller
  MultiController(DoctusClickableSwing(ba),
    DoctusClickableSwing(bb),
    DoctusClickableSwing(bc),
    DoctusClickableSwing(bd),
    c, d, sl)

  // Open main frame
  val f = new MainFrame {
    title = "Sumosim Multi"
    contents = panel
  }
  f.size = new Dimension(800, 600)
  f.open()

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
    UpDownLeftRight(up, down, left, right, sl)
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
    UpDownLeftRight(up, down, left, right, sl)
  }
  val ex = new ManualVsStandstillExample(c, d, Some(sd), sl)
  new SumoApp(ex, p)
}

object ManualVsForwardBackwardButtonsApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val bup = new Label {
    text = "UP"
  }
  val bdown = new Label {
    text = "DOWN"
  }
  val bleft = new Label {
    text = "LEFT"
  }
  val bright = new Label {
    text = "RIGHT"
  }

  val d = {
    val up = DoctusActivatableSwing(bup)
    val down = DoctusActivatableSwing(bdown)
    val left = DoctusActivatableSwing(bleft)
    val right = DoctusActivatableSwing(bright)
    UpDownLeftRight(up, down, left, right, sl)
  }
  val ex = new ManualVsForwardBackwardExample(c, d, Some(sd), sl)
  new SumoButtonsApp(ex, p, bup, bdown, bleft, bright)
}

object ManualVsStandstillButtonsApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val bup = new Label {
    text = "UP"
  }
  val bdown = new Label {
    text = "DOWN"
  }
  val bleft = new Label {
    text = "LEFT"
  }
  val bright = new Label {
    text = "RIGHT"
  }

  val d = {
    val up = DoctusActivatableSwing(bup)
    val down = DoctusActivatableSwing(bdown)
    val left = DoctusActivatableSwing(bleft)
    val right = DoctusActivatableSwing(bright)
    UpDownLeftRight(up, down, left, right, sl)
  }
  val ex = new ManualVsStandstillExample(c, d, Some(sd), sl)
  new SumoButtonsApp(ex, p, bup, bdown, bleft, bright)
}

