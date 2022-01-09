package fr.esgi.al.funprog

import play.api.libs.json._

trait Action {
  def writes(): JsValue
}

object Action {
  def apply(str: String): Action = str match {
    case "G" => new Left("G")
    case "D" => new Right("D")
    case "A" => new Advance("A")
  }
}

case class Left(str: String) extends Action {
  override def writes(): JsValue = Json.toJson(str)
}

case class Right(str: String) extends Action {
  override def writes(): JsValue = Json.toJson(str)
}

case class Advance(str: String) extends Action {
  override def writes(): JsValue = Json.toJson(str)
}
