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
  a.onClick(() => new ManualVsStandstillExample(canv, comp, None, sched).start())
  b.onClick(() => new ManualVsForwardBackwardExample(canv, comp, None, sched).start())
  c.onClick(() => new CleverVsForwardBackwardExample(canv, None, sched).start())
  d.onClick(() => new RotatingVsForwardBackwardExample(canv, None, sched).start())
}
