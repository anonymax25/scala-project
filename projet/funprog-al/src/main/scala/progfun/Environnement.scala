package fr.esgi.al.funprog

@SuppressWarnings(Array("org.wartremover.warts.Var"))
class Environnement(height: Int, width: Int, var mowers: List[Mower]) {

  def display(): Unit = {
    println("Display environnement:")
    for (y <- height to 0 by -1) {
      for (x <- 0 to width) {
        print(mowers.find(m => m.x == x && m.y == y) match {
          case Some(m) => " " + m.direction.toString + " "
          case None    => " - "
        })
      }
      println()
    }
  }

  def play(): Unit = mowers.filter(m => m.actions.length > 0) match {
    case first :: rest => {
      applyAction(first)
    }
    case Nil => println("no mowers to play")
  }

  def applyAction(mower: Mower): Unit = mower.actions match {
    case Nil => println("yo")
    case first :: rest => {
      runAction(mower, first)
      mower.actions = rest
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

  def turnRight(mower: Mower) = mower.direction match {
    case North() => mower.direction = new East()
    case South() => mower.direction = new West()
    case East()  => mower.direction = new South()
    case West()  => mower.direction = new North()
  }

  def turnLeft(mower: Mower) = mower.direction match {
    case North() => mower.direction = new West()
    case South() => mower.direction = new East()
    case East()  => mower.direction = new North()
    case West()  => mower.direction = new South()
  }

  def advance(mower: Mower) = mower.direction match {
    case North() =>
      if (isOkMove(mower.x, mower.y + 1)) {
        mower.y = mower.y + 1
      }
    case South() =>
      if (isOkMove(mower.x, mower.y - 1)) {
        mower.y = mower.y - 1
      }
    case East() =>
      if (isOkMove(mower.x + 1, mower.y)) {
        mower.x = mower.x + 1
      }
    case West() =>
      if (isOkMove(mower.x - 1, mower.y)) {
        mower.x = mower.x - 1
      }
  }

  def isOkMove(x: Int, y: Int): Boolean =
    x < 0 || x > width || y < 0 || y > height || mowers
      .filter(m => m.x == x && m.y == y)
      .length == 0
}
