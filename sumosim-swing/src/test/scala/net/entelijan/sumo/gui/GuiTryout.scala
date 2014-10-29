package net.entelijan.sumo.gui

import scala.swing._
import scala.swing.event._
import scala.swing.GridBagPanel._

object GuiTryout extends SimpleSwingApplication {

  val button1 = new Button() {
    val r: Reactions.Reaction = {
      case MouseClicked(src, pt, mod, clicks, pops) => println("MouseClicked")
      case ButtonClicked(src) => println("ButtonClicked")
      case e: Any => println(s"some event $e")
    }
    subscribe(r)
  }


  lazy val ui = new GridBagPanel {

    def createConstraint: Constraints = {
      val c = new Constraints
      c.fill = Fill.Both
      c
    }
    {
      button1.text = "Test Button"
      val c = createConstraint
      c.weightx = 0.5

      c.gridx = 0
      c.gridy = 0
      layout(button1) = c
    }

    {
      val button2 = new Button("Button 2")
      val c = createConstraint
      c.weightx = 0.5
      c.gridx = 1
      c.gridy = 0
      layout(button2) = c
    }
    {
      val button3 = new Button("Button 3")
      val c = createConstraint
      c.weightx = 0.5
      c.gridx = 2
      c.gridy = 0
      layout(button3) = c
    }
    {
      val button4 = new Button("Long-Named Button 4")
      val c = createConstraint
      c.ipady = 40; //make this component tall
      c.weightx = 1.0
      c.weighty = 1.0
      c.gridwidth = 3
      c.gridx = 0
      c.gridy = 1
      layout(button4) = c
    }
    {
      val button5 = new Button("5")
      val c = createConstraint
      c.weighty = 1.0; //request any extra vertical space
      c.anchor = Anchor.PageEnd
      c.insets = new Insets(10, 0, 10, 0); //top padding
      c.gridx = 1; //aligned with button 2
      c.gridwidth = 2; //2 columns wide
      c.gridy = 2; //third row
      layout(button5) = c

    }
  }

  val r1: Reactions.Reaction = {
    case MouseClicked(src, pt, mod, clicks, pops) => println("1 MouseClicked")
    case ButtonClicked(src) => println("1 ButtonClicked")
    case e: Any => println(s"1 some event $e")
  }
  ui.subscribe(r1)
  
  def top = new MainFrame {
    title = "GridBag Demo"
    contents = ui
  }
}