package fr.esgi.al.funprog

import better.files._
import fr.esgi.al.funprog._

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
class InputLoader(filePath: String) {

  def getFirstLine(lines: List[String]): String = lines match {
    case Nil           => throw DonneesIncorectesException("No lines in the file!")
    case first :: Nil  => first
    case first :: rest => first
  }

  def getMowersLines(lines: List[String]): List[String] = lines match {
    case Nil => throw DonneesIncorectesException("No mower lines in the file!")
    case first :: Nil =>
      throw DonneesIncorectesException("No mower lines in the file!")
    case first :: rest => rest
  }

  def parseInput(): (Int, Int, List[Mower]) = {
    val f: File = File(this.filePath)
    val lines: List[String] = f.lines.toList
    val sizeLine: String = getFirstLine(lines)
    val mowerLines: List[String] = getMowersLines(lines)

    if (mowerLines.size % 2 != 0) {
      throw DonneesIncorectesException(
        "Even number of lines, each mower must have 1 line for its initial postion and 1 line fir his directions list!"
      )
    }

    val sizes: List[Int] = parseSizeLine(sizeLine)
    if (sizes.size != 2) {
      throw DonneesIncorectesException(
        "You must have two numbers separated by a space in the first line of the input, height and width of the area!"
      )
    }

    val groupedMowerLines = mowerLines.grouped(2).toList
    val mowers: List[Mower] = parseMowerLines(groupedMowerLines)

    (sizes(0), sizes(1), mowers)
  }

  def parseSizeLine(sizeLine: String): List[Int] =
    sizeLine.split(" ").toList.map(s => s.toInt)

  def parseMowerLines(mowerLines: List[List[String]]): List[Mower] =
    mowerLines.map(l => {
      val coords = l(0).split(" ").toList
      if (coords.size != 3) {
        throw DonneesIncorectesException(
          "wrong line for mower init position!"
        )
      }
      val actions: List[Action] =
        l(1).split("").toList.map(s => Action.getActionFromString(s))
      val position = new Position(
        coords(0).toInt,
        coords(1).toInt,
        Direction.getFromString(coords(2))
      )
      new Mower(
        new Position(
          coords(0).toInt,
          coords(1).toInt,
          Direction.getFromString(coords(2))
        ),
        new Position(
          coords(0).toInt,
          coords(1).toInt,
          Direction.getFromString(coords(2))
        ),
        actions,
        actions
      )
    })

}
