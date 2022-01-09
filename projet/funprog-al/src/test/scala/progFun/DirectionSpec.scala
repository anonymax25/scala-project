import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class DirectionSpec extends AnyFunSuite {

  test("Parse direction strings to direction sub type-class") {
    val east = Direction("E")
    val west = Direction("W")
    val south = Direction("S")
    val north = Direction("N")

    assert(east.equals(East()))
    assert(west.equals(West()))
    assert(south.equals(South()))
    assert(north.equals(North()))
  }

  test("Turn right on each directions should work as expected") {
    val east = Direction("E")
    val west = Direction("W")
    val south = Direction("S")
    val north = Direction("N")

    //turn right
    assert(east.turnLeft.equals(North()))
    assert(west.turnLeft.equals(South()))
    assert(south.turnLeft.equals(East()))
    assert(north.turnLeft.equals(West()))
  }

  test("Turn left on each directions should work as expected") {
    val east = Direction("E")
    val west = Direction("W")
    val south = Direction("S")
    val north = Direction("N")

    assert(east.turnRight.equals(South()))
    assert(west.turnRight.equals(North()))
    assert(south.turnRight.equals(West()))
    assert(north.turnRight.equals(East()))
  }
}
