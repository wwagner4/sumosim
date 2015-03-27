package net.entelijan.sumo.scalajs

import doctus.core.DoctusCanvas
import doctus.scalajs._
import net.entelijan.sumo.gui.example.{MultiController, ManualVsStandstillExample}
import net.entelijan.sumo.robot.{ControlerValue, UpDownLeftRight}
import org.scalajs.dom
import org.scalajs.jquery.jQuery
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom.ext.KeyCode
import org.scalajs.dom.raw.HTMLCanvasElement
import org.scalajs.dom.raw.HTMLElement

@JSExport(name="ScalajsMultiMain")
object ScalajsMultiMain {

  // Comes here on every refresh (update)
  @JSExport
  def main(): Unit = {
    // Create a scheduler
    val dsc = DoctusSchedulerScalajs

    // GUI Components form the HTML-Page
    val canvas: HTMLCanvasElement = dom.document.getElementById("canvas").asInstanceOf[HTMLCanvasElement]
    val dcanvas: DoctusCanvas = DoctusCanvasScalajs(canvas)
    val dup = DoctusActivatableScalajsKey(dom.document.body, KeyCode.up)
    val ddown = DoctusActivatableScalajsKey(dom.document.body, KeyCode.down)
    val dleft = DoctusActivatableScalajsKey(dom.document.body, KeyCode.left)
    val dright = DoctusActivatableScalajsKey(dom.document.body, KeyCode.right)

    val da = DoctusActivatableScalajs(dom.document.getElementById("a").asInstanceOf[HTMLElement])
    val db = DoctusActivatableScalajs(dom.document.getElementById("b").asInstanceOf[HTMLElement])
    val dc = DoctusActivatableScalajs(dom.document.getElementById("c").asInstanceOf[HTMLElement])
    val dd = DoctusActivatableScalajs(dom.document.getElementById("d").asInstanceOf[HTMLElement])

    val d = new UpDownLeftRight(dup, ddown, dleft, dright, dsc)
    
    
    MultiController(da, db, dc, dd, dcanvas, d, dsc)
  }

}

