package net.entelijan.sumo.gui

import java.awt.Dimension
import javax.swing.{JFrame}
import net.entelijan.sumo.gui.renderer.SumoGuiExample
import java.awt.Component
import java.awt.BorderLayout

class SumoApp(example: SumoGuiExample, canvas: Component) {
  example.start()
  private val f = new JFrame()
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  f.setTitle(example.name)
  f.getContentPane.add(canvas, BorderLayout.CENTER)
  f.setSize(new Dimension(800, 600))
  f.setVisible(true)
}


