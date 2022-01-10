import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class CSVExporterSpec extends AnyFunSuite {

  test("Should export csv of a mower") {
    val mower = SpecHelper.getTestMower(0, 1)

    assert(CSVExporter.export(mower) == "1,0,1,N,0,1,N,AADGA")
  }

  test("Should export csv of a point") {
    val point = new Point(0, 0)

    assert(CSVExporter.export(point) == "0,0,")
  }

  test("Should export csv of all mowers") {
    val mowers = List(
      SpecHelper.getTestMower(0, 1),
      SpecHelper.getTestMower(1, 0)
    )

    assert(
      CSVExporter
        .export(mowers) == "numéro,début_x,début_y,début_direction,fin_x,fin_y,fin_direction,instructions\n1,0,1,N,0,1,N,AADGA\n1,1,0,N,1,0,N,AADGA"
    )
  }
}
