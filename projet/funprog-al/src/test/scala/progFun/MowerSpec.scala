import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class MowerSpec extends AnyFunSuite {

  test("Mower should exist and verify it has an id") {
    val mower = SpecHelper.getTestMower()

    assert(mower.id === 1)
  }

  test("Mower should advance a mower up once") {
    val mower = SpecHelper.getTestMower()

    val updated = mower.advance(new Point(5, 5), Nil)

    assert(updated.point.x === new Point(0, 1).x)
    assert(updated.point.y === new Point(0, 1).y)
  }

  test("Mower should not advance when advancing out of bounds") {
    val mower = SpecHelper.getTestMower()

    val updated = mower.advance(new Point(0, 0), List())

    assert(updated.point.x === mower.point.x)
    assert(updated.point.y === mower.point.y)
  }

  test("Mower should not advance when advancing on another mower") {
    val mower_1 = SpecHelper.getTestMower()
    val mower_2 = SpecHelper.getTestMower(0, 1)

    val updated = mower_1.advance(new Point(2, 2), List(mower_2))

    assert(updated.point.equals(mower_1.point))
  }

  test("Mower direction should go from North to East when turning right") {
    val mower = SpecHelper.getTestMower()
    val updated = mower.turnRight
    assert(updated.direction === new East())
  }

  test("Mower direction should go from North to West when turning left") {
    val mower = SpecHelper.getTestMower()
    val updated = mower.turnLeft
    assert(updated.direction === new West())
  }
}
