package net.entelijan.sumo.gui

import scala.swing._

object GuiEventTryout extends SimpleSwingApplication {

  val ui = new BorderPanel {
    val canvas = new Panel {
      background = java.awt.Color.WHITE
      reactions += {
        case a: Any => println(f"Received: '$a'")
      }
      listenTo(mouse.clicks, mouse.wheel)
    }
    add(canvas, BorderPanel.Position.Center)
  }

  def top = new MainFrame {
    title = "Event Demo"
    contents = ui

    minimumSize = new java.awt.Dimension(200, 100)
    location = new java.awt.Point(500, 300)
  }

}