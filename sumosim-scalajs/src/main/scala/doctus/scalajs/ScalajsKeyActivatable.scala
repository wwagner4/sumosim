package doctus.scalajs

import doctus.core.DoctusActivatable
import org.scalajs.dom.Element
import org.scalajs.dom.Event
import org.scalajs.dom.KeyboardEvent

// TODO move to doctus
case class DoctusActivatableScalajsKey(elem: Element, keycode: scala.scalajs.js.Number) extends DoctusActivatable {

  private var onActOpt: Option[() => Unit] = None
  private var onDeactOpt: Option[() => Unit] = None

  private var active = false

  elem.addEventListener("keydown", (e: Event) => {
    val kevent: KeyboardEvent = e.asInstanceOf[KeyboardEvent]
    if ((kevent.keyCode == keycode.toInt) && !active) {
      e.preventDefault()
      e.cancelBubble
      active = true
      onActOpt.foreach(f => f())
    }
  })

  elem.addEventListener("keyup", (e: Event) => {
    val kevent: KeyboardEvent = e.asInstanceOf[KeyboardEvent]
    if ((kevent.keyCode == keycode.toInt) && active) {
      e.preventDefault()
      e.cancelBubble
      active = false
      onDeactOpt.foreach(f => f())
    }
  })

  def onActivated(f: () => Unit): Unit = onActOpt = Some(f)
  def onDeactivated(f: () => Unit): Unit = onDeactOpt = Some(f)

}