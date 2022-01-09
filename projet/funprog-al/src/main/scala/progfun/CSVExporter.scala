package fr.esgi.al.funprog

import fr.esgi.al.funprog._

class CSVExporter {
  val sep: String = ","
}

object CSVExporter {

  def export(m: Mower): String =
    s"${CSVExporter.export(m.id)}${CSVExporter.export(m.startPoint)}${CSVExporter
      .export(m.startDirection)}${CSVExporter.export(m.point)}${CSVExporter
      .export(m.direction)}${CSVExporter
      .export(m.originalActions)}"

  def export(p: Point): String = s"${p.x.toString},${p.y.toString},"

  def export(i: Int): String = s"${i.toString},"

  def export(d: Direction): String = {
    val char = d match {
      case North() => "N"
      case South() => "S"
      case East()  => "E"
      case West()  => "W"
    }
    s"${char},"
  }

  def export(a: Action): String = {
    val char = a match {
      case Advance(str) => str
      case Left(str)    => str
      case Right(str)   => str
    }
    s"${char}"
  }

  def export(actions: List[Action]): String = {
    actions.map(a => export(a)).mkString("")
  }
}
