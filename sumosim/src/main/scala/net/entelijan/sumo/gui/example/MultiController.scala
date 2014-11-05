package net.entelijan.sumo.gui.example

import doctus.core.{DoctusClickable, DoctusActivatable, DoctusCanvas, DoctusScheduler}
import net.entelijan.sumo.robot.UpDownLeftRight

/**
 * Controls multiple example simulations
 */
case class MultiController(a: DoctusClickable, b: DoctusClickable,
                           c: DoctusClickable, d: DoctusClickable,
                           canv: DoctusCanvas, comp: UpDownLeftRight,
                           sched: DoctusScheduler) {
  new CleverVsForwardBackwardExample(canv, None, sched).start()
  a.onClick(() => {
    println("clicked A")
    new ManualVsStandstillExample(canv, comp, None, sched).start()
  })
  b.onClick(() => {
    println("clicked B")
    new ManualVsForwardBackwardExample(canv, comp, None, sched).start()
  })
  c.onClick(() => {
    println("clicked C")
    new CleverVsForwardBackwardExample(canv, None, sched).start()
  })
  d.onClick(() => {
    println("clicked D")
    new RotatingVsForwardBackwardExample(canv, None, sched).start()
  })
}
