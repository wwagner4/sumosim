package net.entelijan.sumo.gui

import scala.swing._
/**
 * Trying swing app in combination with centerOnScreen.
 * Conclusion: centerOnScreen works incorrect on Mac
 */
object AppTryout extends SimpleSwingApplication {

  val w = 100
  val h = 600
  val top = new MainFrame() {
    contents = new Panel {
      background = java.awt.Color.GREEN
    }
  }

  val ss = java.awt.Toolkit.getDefaultToolkit.getScreenSize
  val px = math.max(0.0, ss.getWidth / 2.0 - w.toDouble / 2.0)
  val py = math.max(0.0, ss.getHeight / 2.0 - h.toDouble / 2.0)
  top.location = new Point(px.toInt, py.toInt)
  top.size = new Dimension(w, h)

}
