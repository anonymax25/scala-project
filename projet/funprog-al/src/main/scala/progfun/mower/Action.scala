package fr.esgi.al.funprog

class Action(str: String) {}

object Action {

  def getActionFromString(str: String): Action = str match {
    case "G" => new Left("G")
    case "D" => new Right("D")
    case "A" => new Advance("A")
  }
}

case class Left(str: String) extends Action(str: String) {}

case class Right(str: String) extends Action(str: String) {}

case class Advance(str: String) extends Action(str: String) {}
