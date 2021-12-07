package fr.esgi.al.funprog

class Environnement(height: Int, width: Int, mowers: List[Mower]) {

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
}
