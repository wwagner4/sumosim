package net.entelijan.sumo.scalajs

import doctus.core._
import doctus.core.util._
import doctus.scalajs._
import net.entelijan.sumo.gui.example._
import net.entelijan.sumo.robot.{ControlerValue, UpDownLeftRight}
import org.scalajs.dom
import org.scalajs.dom.{HTMLCanvasElement, HTMLDivElement}

import scala.scalajs.js.annotation.JSExport

@JSExport(name="ScalajsTouchMain")
object ScalajsTouchMain {

  // Comes here on every refresh (update)
  @JSExport
  def main(): Unit = {
    // GUI Components form the HTML-Page
    println("enterd main")

    val contrCombi: HTMLDivElement = dom.document.getElementById("contrCombi").asInstanceOf[HTMLDivElement]
    val sc = doctus.scalajs.DoctusSchedulerScalajs

    // Wrap the javascript components
    val canvas: HTMLCanvasElement = dom.document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]
    val dcanvas: DoctusCanvas = DoctusCanvasScalajs(canvas)

    val dup = DoctusActivatableScalajsTouchRegion(HTMLElementOffsetProvider(contrCombi), DoctusRectImpl(0, 0, 200, 66))
    val down = DoctusActivatableScalajsTouchRegion(HTMLElementOffsetProvider(contrCombi), DoctusRectImpl(0, 133, 200, 66))
    val left = DoctusActivatableScalajsTouchRegion(HTMLElementOffsetProvider(contrCombi), DoctusRectImpl(0, 0, 66, 200))
    val right = DoctusActivatableScalajsTouchRegion(HTMLElementOffsetProvider(contrCombi), DoctusRectImpl(133, 0, 66, 200))

    val em = DoctusElementEventManager
    // Register the touch elements
    ScalajsTouchElem(List(dup, down, left, right), em)

    val d = UpDownLeftRight(dup, down, left, right, DoctusSchedulerScalajs)

    println("before call of controller")
    new ManualVsStandstillExample(dcanvas, d, None, sc).start()
  }

}

