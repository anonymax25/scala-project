package fr.esgi.al.funprog

import play.api.libs.json._

class Environnement(
    val limit_x: Int,
    val limit_y: Int,
    val mowers: List[Mower],
    val isVerbose: Boolean,
    val delay: Int
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

  def play: List[Mower] = mowers match {
    case first :: rest => {
      val updated = applyAction(first)
      if (updated.playedActions.length > 0) {
        updated :: rest
      } else {
        rest :+ updated
      }
    }
    case Nil => Nil
  }

  def applyAction(mower: Mower): Mower = mower.playedActions match {
    case Nil => mower
    case first :: rest => {
      val updated = runAction(mower, first)
      new Mower(
        updated.id,
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
    case Advance(_) => mower.advance(new Point(limit_x, limit_y), mowers)
    case _          => mower
  }
}

object Environnement {

  def execute(env: Environnement): Environnement =
    env.mowers.filter(m => m.playedActions.length > 0) match {
      case first :: rest => {
        if (env.isVerbose) {
          print("\u001b[2J")
          env.display()
          Thread.sleep(env.delay.toLong)
        }
        execute(
          new Environnement(
            env.limit_x,
            env.limit_y,
            env.play,
            env.isVerbose,
            env.delay
          )
        )
      }
      case Nil => {
        if (env.isVerbose) {
          print("\u001b[2J")
          env.display()
        }
        env
      }
    }

  implicit val environnementWrites = new Writes[Environnement] {
    def writes(env: Environnement): JsValue = {
      Json.obj(
        "limit" -> Json.obj(
          "x" -> env.limit_x,
          "y" -> env.limit_y
        ),
        "tondeuse" -> env.mowers.map(m => Mower.mowersWrites.writes(m))
      )
    }
  }
}
