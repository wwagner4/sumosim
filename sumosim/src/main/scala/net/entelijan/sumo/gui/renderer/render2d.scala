package net.entelijan.sumo.gui.renderer

import net.entelijan.sumo.commons._
import net.entelijan.util._
import doctus.core.DoctusCanvas
import doctus.core.DoctusGraphics
import doctus.core.color._

class R2DRobot(xpos: Double, ypos: Double, dir: Double, val imgProv: ImageProvider) {
  def pos = new Point2(xpos, ypos)
  def direction = dir
}

object R2DUniverse {

  def createUniverse(
    canvas: DoctusCanvas,
    scale: Double,
    provider1: ImageProvider, provider2: ImageProvider): RenderUniverse = {
    val univ = new R2DUniverse(canvas, scale, provider1, provider2)
    univ
  }
  def createSumosUniverse(canvas: DoctusCanvas, scale: Double) = createUniverse(canvas, scale, ImageProvider.sumoBlue, ImageProvider.sumoViolet)
  def createRoboUniverse(canvas: DoctusCanvas, scale: Double) = createUniverse(canvas, scale, ImageProvider.roboBlack, ImageProvider.roboRed)

}

class R2DUniverse(canvas: DoctusCanvas, initialScaleFactor: Double, val provider1: ImageProvider, val provider2: ImageProvider)
  extends RenderUniverse(canvas) {

  var scaleFactor = initialScaleFactor
  var robots: Seq[R2DRobot] = Seq()
  var info = "Initial Info String"

  def receive(umsg: UpdatableMsg) {
    umsg match {
      case msg: SumoSimulationMessage =>
        val r1 = new R2DRobot(msg.xpos1, msg.ypos1, msg.dir1, provider1)
        val r2 = new R2DRobot(msg.xpos2, msg.ypos2, msg.dir2, provider2)
        robots = Seq(r1, r2)
        info = msg.info
        canvas.repaint()
      case InfoMessage(msg) =>
        info = msg
        canvas.repaint()
      case any: UpdatableMsg => ()
    }
  }

  canvas.onRepaint((g: DoctusGraphics) => {

    val cw = canvas.width.toDouble
    val ch = canvas.height.toDouble
    val ratio = ch / cw
    val fillFact = 0.94
    scaleFactor = fillFact * (if (ratio < 0.7) ch / (810 * 0.7) else cw / 810)

    def calculateScreenPosition(pos: Point2) = {
      // 0.7 in respect to the pad.png
      val x = pos.xpos * 0.7 * scaleFactor
      val y = pos.ypos * scaleFactor
      val xoff = canvas.height * 0.5
      val yoff = canvas.width * 0.5
      new Point2(y + yoff, x + xoff)
    }

    def paintBackground() {
      g.setColor(DoctusColorBlack)
      g.fillRect(0, 0, canvas.width, canvas.height)
      val centerScreenPos = calculateScreenPosition(new Point2(0, 0))
      val x = centerScreenPos.xpos - 430 * scaleFactor
      val y = centerScreenPos.ypos - 310 * scaleFactor
      g.drawImage("robot-2d/bg/pad.png", x.toInt, y.toInt, scaleFactor * 0.89)
    }

    def paintRobot(robot: R2DRobot) {
      val p = calculateScreenPosition(robot.pos)
      val img = robot.imgProv.image(robot.direction)
      val w = img.width * scaleFactor * img.scaleFact
      val h = img.height * scaleFactor * img.scaleFact
      val x = p.xpos - (w * robot.imgProv.xoff)
      val y = p.ypos - (h * robot.imgProv.yoff)
      g.drawImage(img.path, x.toInt, y.toInt, scaleFactor * img.scaleFact)
    }

    def sortRobots = {
      def cameraDistance(r: R2DRobot) = r.pos.xpos - 3000
      robots sortWith ((r1, r2) => cameraDistance(r1) < cameraDistance(r2))
    }

    paintBackground()
    sortRobots.foreach(r => paintRobot(r))
    g.setColor(DoctusColorWhite)
    g.drawString(info, 10, 30)

  })
}

/**
 * Scale images and buffer them for quick access
 */
case class Img(path: String, width: Int, height: Int, scaleFact: Double)

/**
 * Define the interface for image providers
 */
trait ImageProvider {
  /**
   * Returns a scaled image for a direction.
   * @param direction The direction in RAD
   */
  def image(direction: Double): Img
  /**
   * The relative offset of the turning point of the provided images.
   * 0.5 means the turning point is in the middle
   */
  def xoff: Double
  /**
   * The relative offset of the turning point of the provided images.
   * 1.0 means the turning point is at the bottom line of the image
   */
  def yoff: Double
}

private trait OctalImageProvider extends ImageProvider with VecUtil with TrigUtil {

  private lazy val scaledImages = (0 to 7) map (i => inputProvider(i))

  def scale: Double

  def image(direction: Double): Img = {
    val index: Int = octal(direction)
    inputProvider(index)
  }

  protected def inputProvider(index: Int): Img
}

private class ClassPathOctalImageProvider(path: String, width: Int, height: Int, val xoff: Double, val yoff: Double, val scale: Double) extends OctalImageProvider {
  protected def imageName(index: Int) = "img%d.png" format index
  def inputProvider(index: Int): Img = {
    val p = "%s/%s" format (path, imageName(index))
    Img(p, width, height, scale)
  }
}

object ImageProvider {

  def sumoViolet: ImageProvider = new ClassPathOctalImageProvider("robot-2d/sumo-violet", 200, 247, 0.5, 0.68, 1.0)
  def sumoBlue: ImageProvider = new ClassPathOctalImageProvider("robot-2d/sumo-blue", 243, 309, 0.5, 0.70, 0.8)
  def roboBlack: ImageProvider = new ClassPathOctalImageProvider("robot-2d/robo1", 150, 146, 0.5, 0.7, 1.0)
  def roboRed: ImageProvider = new ClassPathOctalImageProvider("robot-2d/robo2", 150, 188, 0.5, 0.8, 0.9)

}






