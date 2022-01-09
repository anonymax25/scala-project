import org.scalatest.funsuite.AnyFunSuite
import fr.esgi.al.funprog._

class CSVExporterSpec extends AnyFunSuite {

  test("Should export csv of mower") {
    val mower = SpecHelper.getTestMower(0, 1)

    assert(CSVExporter.export(mower) == "1,0,1,N,0,1,N,AADGA")
  }
}
