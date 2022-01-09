package fr.esgi.al.funprog
import play.api.libs.json._

class Point(
    val x: Int,
    val y: Int
) {
  val toJson: JsValue = Json.obj(
    "x" -> x,
    "y" -> y
  )

  def equals(p: Point): Boolean = p.x == x && p.y == y
}
