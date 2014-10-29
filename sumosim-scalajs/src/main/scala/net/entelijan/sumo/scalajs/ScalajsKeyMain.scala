package net.entelijan.sumo.scalajs

import doctus.core.DoctusCanvas
import doctus.scalajs._
import net.entelijan.sumo.gui.example.ManualVsStandstillExample
import net.entelijan.sumo.robot.{ControlerValue, UpDownLeftRight}
import org.scalajs.dom
import org.scalajs.dom.HTMLCanvasElement
import org.scalajs.dom.extensions.KeyCode

import scala.scalajs.js.annotation.JSExport

@JSExport
object ScalajsKeyMain {

  // Comes here on every refresh (update)
  @JSExport
  def main(): Unit = {
    // GUI Components form the HTML-Page

    val canvas: HTMLCanvasElement = dom.document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]

    val dsc = DoctusSchedulerScalajs

    // Wrap the javascript components
    val dcanvas: DoctusCanvas = DoctusCanvasScalajs(canvas)

    val dup= DoctusActivatableScalajsKey(dom.document.body, KeyCode.up)
    val ddown = DoctusActivatableScalajsKey(dom.document.body, KeyCode.down)
    val dleft = DoctusActivatableScalajsKey(dom.document.body, KeyCode.left)
    val dright = DoctusActivatableScalajsKey(dom.document.body, KeyCode.right)

    val d =
      new UpDownLeftRight(
        ControlerValue(dup, dsc),
        ControlerValue(ddown, dsc),
        ControlerValue(dleft, dsc),
        ControlerValue(dright, dsc))

    // new ManualVsForwardBackwardExample(dcanvas, d, None, dsc).start
    new ManualVsStandstillExample(dcanvas, d, None, dsc).start()
  }

}

