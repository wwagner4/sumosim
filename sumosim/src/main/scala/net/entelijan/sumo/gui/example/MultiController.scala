package net.entelijan.sumo.gui.example

import doctus.core.DoctusScheduler.Stopper
import doctus.core.{DoctusCanvas, DoctusClickable, DoctusScheduler}
import net.entelijan.sumo.robot.UpDownLeftRight

/**
 * Controls multiple example simulations
 */
case class MultiController(a: DoctusClickable, b: DoctusClickable,
                           c: DoctusClickable, d: DoctusClickable,
                           canv: DoctusCanvas, comp: UpDownLeftRight,
                           sched: DoctusScheduler) {
  
  val ssched = StopperCollectionScheduler(sched)

  new CleverVsForwardBackwardExample(canv, None, ssched).start()
  a.onClick(() => {
    ssched.stopAll()
    new CleverVsCleverExample(canv, None, ssched).start()
  })
  b.onClick(() => {
    ssched.stopAll()
    new RotatingVsForwardBackwardExample(canv, None, ssched).start()
  })
  c.onClick(() => {
    ssched.stopAll()
    new ManualVsStandstillExample(canv, comp, None, ssched).start()
  })
  d.onClick(() => {
    ssched.stopAll()
    new ManualVsForwardBackwardExample(canv, comp, None, ssched).start()
  })
}

/**
 * Collects the stoppers of all scheduler threads created by the scheduler
 */
case class StopperCollectionScheduler(sched: DoctusScheduler) extends DoctusScheduler {

  private var stoppers = List.empty[DoctusScheduler.Stopper]

  override def start(f: () => Unit, duration: Int): Stopper = {
    synchronized {
      val stopper = sched.start(f, duration)
      stoppers ::= stopper
      stopper
    }
  }

  /**
   * Stops all schedulers threads
   */
  def stopAll(): Unit = {
    stoppers.foreach(_.stop)
    stoppers = List.empty[DoctusScheduler.Stopper]
  }
}

