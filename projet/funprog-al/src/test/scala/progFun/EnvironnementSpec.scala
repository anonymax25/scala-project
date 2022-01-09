import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class EnvironnementSpec extends AnyFunSuite {

  test("Should get env filled with mowers") {
    val env = SpecHelper.getTestEnv()

    assert(env.mowers.length == 2)
    assert(env.limit_x == 5)
    assert(env.limit_y == 5)
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
}
