package net.entelijan.sumo.robot

import doctus.core._


// TODO Implement as trait
case class ControlerValue(activatable: DoctusActivatable, sched: DoctusScheduler)

// TODO Implement as trait
case class UpDownLeftRight(
  up: ControlerValue,
  down: ControlerValue,
  left: ControlerValue,
  right: ControlerValue)

class ManualController(val name: String, comp: UpDownLeftRight) extends DiffDriveController[NullSensor] {

  private var left = 0.0
  private var right = 0.0
  private var forw = 0.0
  private var backw = 0.0

  trait KeyListener {

    trait State
    case object Idle extends State
    case object Increase extends State
    case object Decrease extends State

    def sched: DoctusScheduler
    
    private var value = 0.0
    private var state: State = Idle

    def maxValue = 1.0

    def minValue = 0.0
    def incValue = 0.01
    def decValue = 0.008
    def pauseTime = 5 // ms

    def valueChanged(value: Double)

    sched.start(() => {
      if (state == Increase && value < maxValue) {
        value = scala.math.min(value + incValue, maxValue)
        valueChanged(value)
      }
      if (state == Decrease && value > minValue) {
        value = scala.math.max(value - decValue, minValue)
        valueChanged(value)
      }
    }, pauseTime)

    def startIncreaseValue() {
      state = Increase
    }
    def startDecreaseValue() {
      state = Decrease
    }
  }

  val leftKeyListener = new KeyListener {
    def sched = comp.left.sched
    def valueChanged(value: Double) {
      left = value
    }
  }
  val rightKeyListener = new KeyListener {
    def sched = comp.right.sched
    def valueChanged(value: Double) {
      right = value
    }
  }
  val forwKeyListener = new KeyListener {
    def sched = comp.up.sched
    def valueChanged(value: Double) {
      forw = value
    }
  }
  val backwKeyListener = new KeyListener {
    def sched = comp.down.sched
    def valueChanged(value: Double) {
      backw = value
    }
  }

  val dbg = false

  comp.left.activatable.onActivated { () =>
    if (dbg) println("## l +")
    leftKeyListener.startIncreaseValue() }
  comp.left.activatable.onDeactivated { () =>
    if (dbg) println("## l -")
    leftKeyListener.startDecreaseValue() }
  comp.right.activatable.onActivated { () =>
    if (dbg) println("## r +")
    rightKeyListener.startIncreaseValue() }
  comp.right.activatable.onDeactivated { () =>
    if (dbg) println("## r -")
    rightKeyListener.startDecreaseValue() }
  comp.up.activatable.onActivated { () =>
    if (dbg) println("## f +")
    forwKeyListener.startIncreaseValue() }
  comp.up.activatable.onDeactivated { () =>
    if (dbg) println("## f -")
    forwKeyListener.startDecreaseValue() }
  comp.down.activatable.onActivated { () =>
    if (dbg) println("## b +")
    backwKeyListener.startIncreaseValue() }
  comp.down.activatable.onDeactivated { () =>
    if (dbg) println("## b -")
    backwKeyListener.startDecreaseValue() }

  // Decrease that value if the controller reacts to sharp
  def forwardFactor = 1.0
  // Decrease that value if the controller reacts to sharp
  def rotFactor = 1.0

  def takeStep(sensor: NullSensor): DiffDriveValues = {
    var rightWheel = (left - right) * 7 * rotFactor + (forw - backw) * 15.0 * forwardFactor
    var leftWheel = (right - left) * 7 * rotFactor + (forw - backw) * 15.0 * forwardFactor
    new DiffDriveValues(leftWheel, rightWheel)
  }

}


