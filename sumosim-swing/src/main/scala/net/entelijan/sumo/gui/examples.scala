package net.entelijan.sumo.gui

import java.awt.event._
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
  val doctPanel = new DoctusPanel
  val ba = new JButton("A")
  val bb = new JButton("B")
  val bc = new JButton("C")
  val bd = new JButton("D")

  // Layout components


  val buttonsPanel = new JPanel()
  buttonsPanel.setLayout(new FlowLayout())
  buttonsPanel.add(ba)
  buttonsPanel.add(bb)
  buttonsPanel.add(bc)
  buttonsPanel.add(bd)

  val contPanel = new JPanel()
  contPanel.setLayout(new BorderLayout())
  contPanel.add(doctPanel, BorderLayout.CENTER)
  contPanel.add(buttonsPanel, BorderLayout.SOUTH)

  // Open main frame
  val f = new JFrame()
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  f.setTitle("Sumosim Multi")
  f.setSize(new Dimension(800, 600))
  f.setContentPane(contPanel)
  f.setVisible(true)

  // Wrap components with DoctusClasses
  val canv = new DoctusCanvasSwing(doctPanel)
  val sl = DoctusSchedulerSwing
  val udlr = {
    val up = DoctusActivatableSwingKey(doctPanel, KeyEvent.VK_UP)
    val down = DoctusActivatableSwingKey(doctPanel, KeyEvent.VK_DOWN)
    val left = DoctusActivatableSwingKey(doctPanel, KeyEvent.VK_LEFT)
    val right = DoctusActivatableSwingKey(doctPanel, KeyEvent.VK_RIGHT)
    UpDownLeftRight(up, down, left, right, sl)
  }
  val ca = DoctusClickableSwing(ba)
  val cb = DoctusClickableSwing(bb)
  val cc = DoctusClickableSwing(bc)
  val cd = DoctusClickableSwing(bd)

  // Start the controller
  MultiController(ca, cb, cc, cd, canv, udlr, sl)

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

