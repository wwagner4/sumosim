package net.entelijan.sumo.scalajs

import doctus.core.DoctusCanvas
import doctus.scalajs._
import net.entelijan.sumo.gui.example.ManualVsStandstillExample
import net.entelijan.sumo.robot.{ControlerValue, UpDownLeftRight}
import org.scalajs.dom
import org.scalajs.dom.HTMLCanvasElement
import org.scalajs.dom.extensions.KeyCode
import org.scalajs.jquery.jQuery


import scala.scalajs.js.annotation.JSExport

@JSExport
object ScalajsMultiMain {

  // Comes here on every refresh (update)
  @JSExport
  def main(): Unit = {
    // Create a scheduler
    val dsc = DoctusSchedulerScalajs

    // GUI Components form the HTML-Page
    val canvas: HTMLCanvasElement = dom.document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]
    val dcanvas: DoctusCanvas = DoctusCanvasScalajs(canvas)
    val dup= DoctusActivatableScalajsKey(dom.document.body, KeyCode.up)
    val ddown = DoctusActivatableScalajsKey(dom.document.body, KeyCode.down)
    val dleft = DoctusActivatableScalajsKey(dom.document.body, KeyCode.left)
    val dright = DoctusActivatableScalajsKey(dom.document.body, KeyCode.right)

    val da = DoctusClickableScalajs(dom.document.getElementById("a"))
    val db = DoctusClickableScalajs(dom.document.getElementById("b"))
    val dc = DoctusClickableScalajs(dom.document.getElementById("c"))
    val dd = DoctusClickableScalajs(dom.document.getElementById("d"))

    val d =
      new UpDownLeftRight(ControlerValue(dup, dsc), ControlerValue(ddown, dsc),
        ControlerValue(dleft, dsc), ControlerValue(dright, dsc))

    // new ManualVsForwardBackwardExample(dcanvas, d, None, dsc).start
    new ManualVsStandstillExample(dcanvas, d, None, dsc).start()
  }

}

