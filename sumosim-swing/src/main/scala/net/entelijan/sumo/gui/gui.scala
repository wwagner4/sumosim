package net.entelijan.sumo.gui

import net.entelijan.sumo.gui.renderer.SumoGuiExample

import scala.swing.{Component, Dimension, GridPanel, MainFrame}

class SumoApp(example: SumoGuiExample, canvas: Component) {
  def mainFrame: MainFrame = {
    new MainFrame {
      title = example.name
      contents = canvas
    }
  }
  example.start()
  private val f = mainFrame
  f.size = new Dimension(800, 600)
  f.open()
}

class SumoButtonsApp(example: SumoGuiExample, canvas: Component, up: Component, down: Component, left: Component, right: Component) {
  def mainFrame: MainFrame = {
    new MainFrame {
      title = example.name
      contents = new GridPanel(1, 3) {
        val leftp = new GridPanel(2, 1) {
          contents += up
          contents += down
        }
        val rightp = new GridPanel(2, 1) {
          contents += left
          contents += right
        }
        contents += leftp
        contents += canvas
        contents += rightp
      }
      size = new Dimension(1000, 400)
    }
  }
  example.start()
  private val f = mainFrame
  f.open()
}

