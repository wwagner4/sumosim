package net.entelijan.sumo.scalajs

import doctus.core.DoctusCanvas
import doctus.core.util.DoctusRectImpl
import doctus.scalajs._
import net.entelijan.sumo.gui.example._
import net.entelijan.sumo.robot.{ControlerValue, UpDownLeftRight}
import org.scalajs.dom
import org.scalajs.dom.{HTMLCanvasElement, HTMLDivElement}

import scala.scalajs.js.annotation.JSExport

@JSExport
object ScalajsMain {

  // Comes here on every refresh (update)
  @JSExport
  def main(): Unit = {
    // GUI Components form the HTML-Page

    val canvas: HTMLCanvasElement = dom.document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val contrAcc: HTMLDivElement = dom.document.getElementById("contrAcc").asInstanceOf[HTMLDivElement]
    val contrTurn: HTMLDivElement = dom.document.getElementById("contrTurn").asInstanceOf[HTMLDivElement]

    val sc = DoctusSchedulerScalajs

    // Wrap the javascript components
    val dcanvas: DoctusCanvas = DoctusCanvasScalajs(canvas)


    val em = DoctusElementEventManager

    new RotatingVsForwardBackwardExample(dcanvas, None, sc).start()
    // new CleverVsForwardBackwardExample(dcanvas, None, sc).start
  }

}

