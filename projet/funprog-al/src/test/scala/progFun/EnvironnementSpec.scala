import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class EnvironnementSpec extends AnyFunSuite {

  test("Should get env filled with mowers") {
    val env = SpecHelper.getTestEnv()

    assert(env.mowers.length == 2)
    assert(env.limit.x == 5)
    assert(env.limit.y == 5)
  }

  test("Env should be executed and run all the mower actions turns") {
    val env = SpecHelper.getTestEnv()

    val executed = Environnement.execute(env)

    // assert 1nd mower position
    assert(executed.mowers(0).point.equals(new Point(1, 3)))
    assert(executed.mowers(0).direction.equals(North()))

    // assert 2nd mower position
    assert(executed.mowers(1).point.equals(new Point(5, 1)))
    assert(executed.mowers(1).direction.equals(East()))

  }

  test("Env play execution should only update the first mower's direction only") {
    val env = SpecHelper.getTestEnv()

    val mowers = env.play

    // mower 1
    assert(mowers(0).startPoint.equals(mowers(0).point))
    assert(mowers(0).direction.equals(West())) // same

    // mower 2
    assert(mowers(1).startPoint.equals(mowers(1).point)) // same
    assert(mowers(1).startDirection.equals(mowers(1).direction)) // same

  }
}
