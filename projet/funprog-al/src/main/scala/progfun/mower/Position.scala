package fr.esgi.al.funprog
import play.api.libs.json._

@SuppressWarnings(Array("org.wartremover.warts.Var"))
class Position(
    var x: Int,
    var y: Int,
    var direction: Direction
) {
  def toJson: JsValue = Json.obj(
    "point" -> Json.obj(
      "x" -> x,
      "y" -> y
    ),
    "direction" -> direction.toString
  )
}
