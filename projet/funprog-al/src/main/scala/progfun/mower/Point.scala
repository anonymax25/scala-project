package fr.esgi.al.funprog
import play.api.libs.json._

class Point(
    val x: Int,
    val y: Int
) {
  def equals(p: Point): Boolean = p.x == x && p.y == y
}

object Point {

  implicit val pointWrites = new Writes[Point] {
    def writes(point: Point): JsValue = {
      Json.obj(
        "x" -> point.x,
        "y" -> point.y
      )
    }
  }
}
