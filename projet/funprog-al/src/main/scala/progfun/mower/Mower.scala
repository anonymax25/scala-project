package fr.esgi.al.funprog

import fr.esgi.al.funprog._
import play.api.libs.json._

class Mower(
    val id: Int,
    val startPoint: Point,
    val startDirection: Direction,
    val point: Point,
    val direction: Direction,
    val playedActions: List[Action],
    val originalActions: List[Action]
) {

  def turnRight: Mower = new Mower(
    id,
    startPoint,
    startDirection,
    point,
    direction.turnRight,
    playedActions,
    originalActions
  )

  def turnLeft: Mower = new Mower(
    id,
    startPoint,
    startDirection,
    point,
    direction.turnLeft,
    playedActions,
    originalActions
  )

  def advance(limit: Point, mowers: List[Mower]): Mower = {
    val newPoint: Point = direction match {
      case North() =>
        if (isOkMove(point.x, point.y + 1, limit, mowers))
          new Point(
            point.x,
            point.y + 1
          )
        else
          point
      case South() =>
        if (isOkMove(point.x, point.y - 1, limit, mowers))
          new Point(
            point.x,
            point.y - 1
          )
        else
          point

      case East() =>
        if (isOkMove(point.x + 1, point.y, limit, mowers))
          new Point(
            point.x + 1,
            point.y
          )
        else
          point
      case West() =>
        if (isOkMove(point.x - 1, point.y, limit, mowers))
          new Point(
            point.x - 1,
            point.y
          )
        else
          point
    }

    new Mower(
      id,
      startPoint,
      startDirection,
      newPoint,
      direction,
      playedActions,
      originalActions
    )
  }

  def isOkMove(x: Int, y: Int, limit: Point, mowers: List[Mower]): Boolean = {
    x >= 0 && x <= limit.x && y >= 0 && y <= limit.y && mowers
      .filter(m => m.point.x == x && m.point.y == y)
      .length == 0
  }
}

object Mower {

  implicit val mowersWrites = new Writes[Mower] {
    def writes(mower: Mower): JsValue = {
      Json.obj(
        "id" -> mower.id,
        "debut" -> Json.obj(
          "point"     -> Point.pointWrites.writes(mower.startPoint),
          "direction" -> mower.startDirection.toString
        ),
        "instructions" -> mower.originalActions.map(a => a.writes()),
        "fin" -> Json.obj(
          "point"     -> Point.pointWrites.writes(mower.point),
          "direction" -> mower.direction.toString
        )
      )
    }
  }
}
