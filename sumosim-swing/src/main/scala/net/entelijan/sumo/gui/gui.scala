package net.entelijan.sumo.gui

import java.awt.Dimension
import javax.swing.{JFrame, JComponent}

import net.entelijan.sumo.gui.renderer.SumoGuiExample

class SumoApp(example: SumoGuiExample, canvas: JComponent) {
  example.start()
  private val f = new JFrame()
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  f.setTitle(example.name)
  f.setContentPane(canvas)
  f.setSize(new Dimension(800, 600))
  f.setVisible(true)
}


