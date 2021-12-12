package fr.esgi.al.funprog

abstract class Direction {
  def toString(): String
}

object Direction {
  def getFromString(direction: String): Direction = direction match {
    case "N" => new North()
    case "S" => new South()
    case "W" => new West()
    case "E" => new East()
  }
}

case class North() extends Direction {
  override def toString: String = "N"
}

case class South() extends Direction {
  override def toString: String = "S"
}

case class East() extends Direction {
  override def toString: String = "E"
}

case class West() extends Direction {
  override def toString: String = "W"
}
