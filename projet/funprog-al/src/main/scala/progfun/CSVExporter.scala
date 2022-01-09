package fr.esgi.al.funprog

import fr.esgi.al.funprog._

trait CSVExporter {}

object CSVExporter {

  val sep = ","

  def export(mowers: List[Mower]): String = {
    val headers = "numéro" :: "début_x" :: "début_y" :: "début_direction" :: "fin_x" :: "fin_y" :: "fin_direction" :: "instructions" :: Nil
    (export(headers) :: mowers.map(mower => export(mower))).mkString("\n")
  }

  def export(m: Mower): String =
    s"${export(m.id)}${export(m.startPoint)}${export(m.startDirection)}${export(
      m.point
    )}${export(m.direction)}${export(m.originalActions)}"

  def export(strings: List[String])(implicit d: DummyImplicit): String =
    strings.mkString(sep)

  def export(p: Point): String = s"${p.x.toString}${sep}${p.y.toString}${sep}"

  def export(i: Int): String = s"${i.toString}${sep}"

  def export(direction: Direction): String = {
    val char = direction match {
      case North() => "N"
      case South() => "S"
      case East()  => "E"
      case West()  => "W"
    }
    s"${char}${sep}"
  }

  def export(action: Action): String = action match {
    case Advance(str) => str
    case Left(str)    => str
    case Right(str)   => str
  }

  def export(
      actions: List[Action]
  )(implicit d1: DummyImplicit, d2: DummyImplicit): String = {
    actions.map(a => export(a)).mkString("")
  }
}
