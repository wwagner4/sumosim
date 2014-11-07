package net.entelijan.sumo.robot

import scala.swing._
import scala.swing.event._

object SwingKeyActivatableTryout extends App {
  val mf = new MainFrame {
    val bp = new BorderPanel

    val k1 = DoctusActivatableSwingKey(bp, Key.Up)
    k1.onActivated {() => println("Pressed UP key")}
    k1.onDeactivated {() => println("Released UP key")}
    
    contents = bp
    
  }

  mf.title = "DoctusActivatableSwingKey"
  mf.size = new Dimension(200, 100)
  mf.open()
}

