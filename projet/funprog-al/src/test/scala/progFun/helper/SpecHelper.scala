package fr.esgi.al.funprog

import fr.esgi.al.funprog._

class SpecHelper {}

@SuppressWarnings(Array("org.wartremover.warts.DefaultArguments"))
object SpecHelper {

  def getTestMower(
      x: Int = 0,
      y: Int = 0,
      directionStr: String = "N",
      actionsStr: String = "AADGA"
  ): Mower = {
    val id = 1
    val point = new Point(x, y)
    val direction = Direction(directionStr)
    val actions: List[Action] =
      actionsStr.split("").toList.map(s => Action(s))

    new Mower(
      id,
      point,
      direction,
      point,
      direction,
      actions,
      actions
    )
  }

  def getTestEnv(): Environnement = {
    val mowers = List(
      getTestMower(1, 2, "N", "GAGAGAGAA"),
      getTestMower(3, 3, "E", "AADAADADDA")
    )

    val limit = new Point(5, 5)

    new Environnement(
      limit,
      mowers,
      false,
      0
    )

  }

}
