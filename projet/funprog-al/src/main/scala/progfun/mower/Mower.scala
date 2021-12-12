package fr.esgi.al.funprog

import fr.esgi.al.funprog._
import play.api.libs.json._

@SuppressWarnings(Array("org.wartremover.warts.Var"))
case class Mower(
    var startPosition: Position,
    var position: Position,
    var playedActions: List[Action],
    val originalActions: List[Action]
) {
  def toJson: JsValue = Json.obj(
    "debut"        -> startPosition.toJson,
    "instructions" -> originalActions.map(a => a.toJson()),
    "fin"          -> position.toJson
  )
}
