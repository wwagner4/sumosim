package net.entelijan.sumo.gui

import java.awt.event.KeyEvent
import java.awt.{Dimension, FlowLayout, BorderLayout}
import javax.swing.{JFrame, JPanel, JButton}

import doctus.swing._
import net.entelijan.sumo.gui.example._
import net.entelijan.sumo.robot._
import net.entelijan.sumo.sound.SoundDesign00

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
  val ba = new JButton("A")
  val bb = new JButton("B")
  val bc = new JButton("C")
  val bd = new JButton("D")

  // Layout components
  val panel = new JPanel()
  panel.setLayout(new BorderLayout())

  panel.add(p, BorderLayout.CENTER)
  val bp = new JPanel()
  bp.setLayout(new FlowLayout())
  bp.add(ba)
  bp.add(bb)
  bp.add(bc)
  bp.add(bd)
  panel.add(bp, BorderLayout.SOUTH)

  // Wrap components with DoctusClasses
  val c = new DoctusCanvasSwing(p)
  val sl = DoctusSchedulerSwing
  val d = {
    val up = DoctusActivatableSwingKey(panel, KeyEvent.VK_UP)
    val down = DoctusActivatableSwingKey(panel, KeyEvent.VK_DOWN)
    val left = DoctusActivatableSwingKey(panel, KeyEvent.VK_LEFT)
    val right = DoctusActivatableSwingKey(panel, KeyEvent.VK_RIGHT)
    UpDownLeftRight(up, down, left, right, sl)
  }

  // Start the controller
  MultiController(DoctusClickableSwing(ba),
    DoctusClickableSwing(bb),
    DoctusClickableSwing(bc),
    DoctusClickableSwing(bd),
    c, d, sl)

  // Open main frame
  val f = new JFrame()
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  f.setTitle("Sumosim Multi")
  f.setSize(new Dimension(800, 600))
  f.setContentPane(panel)
  f.setVisible(true)
}

object ManualVsForwardBackwardApp extends App {
  val p = new DoctusPanel
  val c = new DoctusCanvasSwing(p)
  val sd = new SoundDesign00
  val sl = DoctusSchedulerSwing

  val d = {
    val up = DoctusActivatableSwingKey(p, KeyEvent.VK_UP)
    val down = DoctusActivatableSwingKey(p, KeyEvent.VK_DOWN)
    val left = DoctusActivatableSwingKey(p, KeyEvent.VK_LEFT)
    val right = DoctusActivatableSwingKey(p, KeyEvent.VK_RIGHT)
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
    val up = DoctusActivatableSwingKey(p, KeyEvent.VK_UP)
    val down = DoctusActivatableSwingKey(p, KeyEvent.VK_DOWN)
    val left = DoctusActivatableSwingKey(p, KeyEvent.VK_LEFT)
    val right = DoctusActivatableSwingKey(p, KeyEvent.VK_RIGHT)
    UpDownLeftRight(up, down, left, right, sl)
  }
  val ex = new ManualVsStandstillExample(c, d, Some(sd), sl)
  new SumoApp(ex, p)
}

