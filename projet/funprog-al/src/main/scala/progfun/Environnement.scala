package fr.esgi.al.funprog

import play.api.libs.json._

class Environnement(
    val limit_x: Int,
    val limit_y: Int,
    val mowers: List[Mower]
) {

  def display(): Unit = {
    println("Display environnement:")
    for (y <- limit_y to 0 by -1) {
      for (x <- 0 to limit_x) {
        print(mowers.find(m => m.point.x == x && m.point.y == y) match {
          case Some(m) => " " + m.direction.toString + " "
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

  def play: List[Mower] =
    mowers.filter(m => m.playedActions.length > 0) match {
      case first :: rest => applyAction(first) :: rest
      case Nil           => Nil
    }

  def applyAction(mower: Mower): Mower = mower.playedActions match {
    case Nil => mower
    case first :: rest => {
      val updated = runAction(mower, first)
      new Mower(
        updated.startPoint,
        updated.startDirection,
        updated.point,
        updated.direction,
        rest,
        updated.originalActions
      )
    }
  }

  def runAction(mower: Mower, action: Action): Mower = action match {
    case Left(_)    => mower.turnLeft
    case Right(_)   => mower.turnRight
    case Advance(_) => mower.advance(new Point(limit_x, limit_y), this.mowers)
    case _          => mower
  }
}

object Environnement {
  def execute(env: Environnement): Environnement =
    env.mowers.filter(m => m.playedActions.length > 0) match {
      case first :: rest => {
        print("\u001b[2J")
        env.display()
        Thread.sleep(250)
        execute(new Environnement(env.limit_x, env.limit_y, env.play))
      }
      case Nil => env
    }
}
// use Writes
