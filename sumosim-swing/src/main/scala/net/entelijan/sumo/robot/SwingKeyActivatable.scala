package net.entelijan.sumo.robot

import doctus.core.DoctusActivatable

import scala.swing._
import scala.swing.event._

// TODO move to doctus
case class DoctusActivatableSwingKey(comp: Component, key: Key.Value) extends DoctusActivatable {

  private var pressFunc: Option[() => Unit] = None
  private var releaseFunc: Option[() => Unit] = None
  
  private var active = false

  comp.focusable = true
  comp.listenTo(comp.keys)

  comp.reactions += {
    case KeyPressed(_, k, _, _) if k == key && !active =>
      active = true
      pressFunc.foreach(f => f())
    case KeyReleased(_, k, _, _) if k == key && active =>
      active = false
      releaseFunc.foreach(f => f())
  }

  def onActivated(f: () => Unit): Unit = pressFunc = Some(f)
  def onDeactivated(f: () => Unit): Unit = releaseFunc = Some(f)

}

