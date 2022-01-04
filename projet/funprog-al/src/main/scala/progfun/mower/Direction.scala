package fr.esgi.al.funprog

abstract class Direction {
  def toString(): String
  def turnRight: Direction
  def turnLeft: Direction
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
  def turnRight: Direction = new East
  def turnLeft: Direction = new West
}

case class South() extends Direction {
  override def toString: String = "S"
  def turnRight: Direction = new West
  def turnLeft: Direction = new East

}

case class East() extends Direction {
  override def toString: String = "E"
  def turnRight: Direction = new South
  def turnLeft: Direction = new North
}

case class West() extends Direction {
  override def toString: String = "W"
  def turnRight: Direction = new North
  def turnLeft: Direction = new South
}
