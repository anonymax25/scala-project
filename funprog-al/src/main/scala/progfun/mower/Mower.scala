package fr.esgi.al.funprog

import fr.esgi.al.funprog._
import play.api.libs.json._

class Mower(
    val startPoint: Point,
    val startDirection: Direction,
    val point: Point,
    val direction: Direction,
    val playedActions: List[Action],
    val originalActions: List[Action]
) {

  def toJson: JsValue = Json.obj(
    "debut" -> Json.obj(
      "point"     -> startPoint.toJson,
      "direction" -> startDirection.toString
    ),
    "instructions" -> originalActions.map(a => a.toJson()),
    "fin" -> Json.obj(
      "point"     -> point.toJson,
      "direction" -> direction.toString
    )
  )

  def turnRight: Mower = new Mower(
    startPoint,
    startDirection,
    point,
    direction.turnRight,
    playedActions,
    originalActions
  )

  def turnLeft: Mower = new Mower(
    startPoint,
    startDirection,
    point,
    direction.turnLeft,
    playedActions,
    originalActions
  )

  def advance(limit: Point, mowers: List[Mower]): Mower = {
    val newPoint: Point = this.direction match {
      case North() =>
        if (isOkMove(this.point.x, this.point.y + 1, point, mowers))
          new Point(
            this.point.x,
            this.point.y + 1
          )
        else
          this.point
      case South() =>
        if (isOkMove(this.point.x, this.point.y - 1, point, mowers))
          new Point(
            this.point.x,
            this.point.y - 1
          )
        else
          this.point

      case East() =>
        if (isOkMove(this.point.x + 1, this.point.y, point, mowers))
          new Point(
            this.point.x + 1,
            this.point.y
          )
        else
          this.point
      case West() =>
        if (isOkMove(this.point.x - 1, this.point.y, point, mowers))
          new Point(
            this.point.x - 1,
            this.point.y
          )
        else
          this.point
    }

    new Mower(
      startPoint,
      startDirection,
      newPoint,
      direction,
      playedActions,
      originalActions
    )
  }

  def isOkMove(x: Int, y: Int, point: Point, mowers: List[Mower]): Boolean =
    x < 0 || x > point.x || y < 0 || y > point.y || mowers
      .filter(m => m.point.x == x && m.point.y == y)
      .length == 0
}
