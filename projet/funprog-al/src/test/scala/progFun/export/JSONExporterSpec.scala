import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._
import play.api.libs.json._

class JSONExporterSpec extends AnyFunSuite {

  test("Should export a mower as json") {
    val mower = SpecHelper.getTestMower(0, 1)
    val result = Json.toJson(mower)
    val expected =
      """{"id":1,"debut":{"point":{"x":0,"y":1},"direction":"N"},"instructions":["A","A","D","G","A"],"fin":{"point":{"x":0,"y":1},"direction":"N"}}"""

    assert(result.toString == expected)
  }

  test("Should export a point as json") {
    val point = new Point(0, 0)
    val result = Json.toJson(point)
    val expected = """{"x":0,"y":0}"""

    assert(result.toString == expected)
  }

  test("Should export mowers list as json") {
    val mowers = List(
      SpecHelper.getTestMower(1, 2),
      SpecHelper.getTestMower(3, 3)
    )
    val result = Json.toJson(mowers)
    val expected =
      """[{"id":1,"debut":{"point":{"x":1,"y":2},"direction":"N"},"instructions":["A","A","D","G","A"],"fin":{"point":{"x":1,"y":2},"direction":"N"}},{"id":1,"debut":{"point":{"x":3,"y":3},"direction":"N"},"instructions":["A","A","D","G","A"],"fin":{"point":{"x":3,"y":3},"direction":"N"}}]"""

    assert(result.toString == expected)
  }
}
