package fr.esgi.al.funprog

import play.api.libs.json._

@SuppressWarnings(Array("org.wartremover.warts.Var"))
class Environnement(limit_x: Int, limit_y: Int, var mowers: List[Mower]) {

  def display(): Unit = {
    println("Display environnement:")
    for (y <- limit_y to 0 by -1) {
      for (x <- 0 to limit_x) {
        print(mowers.find(m => m.position.x == x && m.position.y == y) match {
          case Some(m) => " " + m.position.direction.toString + " "
          case None    => " - "
        })
      }
      println()
    }
  }

  def toJson: JsValue = Json.obj(
    "limit" -> Json.obj(
      "x" -> limit_x,
      "y" -> limit_y
    ),
    "tondeuse" -> mowers.map(m => m.toJson)
  )

  def play(): Unit = mowers.filter(m => m.playedActions.length > 0) match {
    case first :: rest => {
      applyAction(first)
    }
    case Nil => println("no mowers to play")
  }

  def applyAction(mower: Mower): Unit = mower.playedActions match {
    case Nil => println("yo")
    case first :: rest => {
      runAction(mower, first)
      mower.playedActions = rest
    }
  }

  def runAction(mower: Mower, action: Action): Unit = {
    action match {
      case Left(_)    => turnLeft(mower)
      case Right(_)   => turnRight(mower)
      case Advance(_) => advance(mower)
      case _          => println("no action")
    }
  }

  def turnRight(mower: Mower) = mower.position.direction match {
    case North() => mower.position.direction = new East()
    case South() => mower.position.direction = new West()
    case East()  => mower.position.direction = new South()
    case West()  => mower.position.direction = new North()
  }

  def turnLeft(mower: Mower) = mower.position.direction match {
    case North() => mower.position.direction = new West()
    case South() => mower.position.direction = new East()
    case East()  => mower.position.direction = new North()
    case West()  => mower.position.direction = new South()
  }

  def advance(mower: Mower) = mower.position.direction match {
    case North() =>
      if (isOkMove(mower.position.x, mower.position.y + 1)) {
        mower.position.y = mower.position.y + 1
      }
    case South() =>
      if (isOkMove(mower.position.x, mower.position.y - 1)) {
        mower.position.y = mower.position.y - 1
      }
    case East() =>
      if (isOkMove(mower.position.x + 1, mower.position.y)) {
        mower.position.x = mower.position.x + 1
      }
    case West() =>
      if (isOkMove(mower.position.x - 1, mower.position.y)) {
        mower.position.x = mower.position.x - 1
      }
  }

  def isOkMove(x: Int, y: Int): Boolean =
    x < 0 || x > limit_x || y < 0 || y > limit_y || mowers
      .filter(m => m.position.x == x && m.position.y == y)
      .length == 0

  def get_limit_x = limit_x
  def get_limit_y = limit_y
}
