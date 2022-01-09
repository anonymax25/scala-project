import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class ActionSpec extends AnyFunSuite {

  test("Parse action") {
    val actionStr = "A"
    val action = Action(actionStr)

    val test = action match {
      case Advance(_) => true
      case Right(_)   => false
      case Left(_)    => false
    }

    assert(test == true)
  }
}
