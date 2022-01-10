import org.scalatest.funsuite.AnyFunSuite
import com.typesafe.config.{Config, ConfigFactory}
import fr.esgi.al.funprog._

class InputLoaderSpec extends AnyFunSuite {
  val conf: Config = ConfigFactory.load()
  val inputLoader = new InputLoader(conf.getString("application.input-file"))

  test("Should parse the input file") {
    val inputs = inputLoader.parseInput()

    assert(inputs._1.equals(5))
    assert(inputs._2.equals(5))
    assert(inputs._3.size.equals(2))
  }
}
