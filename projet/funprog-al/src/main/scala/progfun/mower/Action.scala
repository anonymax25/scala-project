package fr.esgi.al.action

abstract class Action {
  def getChar(action: Action): String = action match {
    case Left()    => "G"
    case Right()   => "D"
    case Advance() => "A"
  }
}

case class Left() extends Action {}

case class Right() extends Action {}

case class Advance() extends Action {}
