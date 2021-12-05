package fr.esgi.al.funprog

import better.files._
import com.typesafe.config.{Config, ConfigFactory}

final case class DonneesIncorectesException(
    val message: String
) extends Exception(message)

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
object Main extends App {

  val conf: Config = ConfigFactory.load()
  val inputFilePath: String = conf.getString("application.input-file")

  val f: File = File(inputFilePath)

  def getFirstLine(lines: List[String]) = lines match {
    case Nil           => throw DonneesIncorectesException("No lines in the file!")
    case first :: Nil  => first
    case first :: rest => first
  }
  def getMowersLines(lines: List[String]) = lines match {
    case Nil => throw DonneesIncorectesException("No mower lines in the file!")
    case first :: Nil =>
      throw DonneesIncorectesException("No mower lines in the file!")
    case first :: rest => rest
  }

  val sizeLine = getFirstLine(f.lines.toList)
  val mowerLines = getMowersLines(f.lines.toList)

  val sizes: List[Int] = sizeLine.split(" ").toList.map(s => s.toInt)
  if (sizes.size != 2) {
    throw DonneesIncorectesException(
      "You must have two numbers separated by a space in the first line of the input, height and width of the area!"
    )
  }

  if (mowerLines.size % 2 != 0) {
    throw DonneesIncorectesException(
      "even number of lines, each mower must have 1 line for its initial postion and 1 line fir his directions list!"
    )
  }
  val mowersLinesGrouped = mowerLines.grouped(2).toList
  val mowers: List[Mower] = mowersLinesGrouped.map(l => {
    val coords = l(0).split(" ").toList
    if (coords.size != 3) {
      throw DonneesIncorectesException(
        "wrong line for mower init position!"
      )
    }
    val actions = l(1).split("").toList
    new Mower(
      coords(0).toInt,
      coords(1).toInt,
      Direction.getFromString(coords(2))
    )
  })

  mowers.foreach(m => println(m))

  val env = new Environnement(sizes(0), sizes(1), mowers)

  env.display()

}

class Environnement(height: Int, width: Int, mowers: List[Mower]) {

  def display(): Unit = {
    println("Display environnement:")
    for (y <- height to 0 by -1) {
      for (x <- 0 to width) {

        val toPrint = mowers.find(m => m.x == x && m.y == y) match {
          case Some(m) => " " + m.direction.toString + " "
          case None    => " - "
        }
        print(toPrint)
      }
      println()
    }
  }
}

case class Mower(x: Int, y: Int, direction: Direction) {}

class Action {}

case class Left() extends Action {
  val str = "G"
}

case class Right() extends Action {
  val str = "D"
}

case class Advance() extends Action {
  val str = "A"
}

abstract class Direction {
  def toString(): String
}

object Direction {
  def getFromString(direction: String): Direction = direction match {
    case "N" => new North()
    case "S" => new South()
    case "W" => new West()
    case "E" => new East()
  }

}

case class North() extends Direction {
  override def toString: String = "N"
}

case class South() extends Direction {
  override def toString: String = "S"
}

case class East() extends Direction {
  override def toString: String = "E"
}

case class West() extends Direction {
  override def toString: String = "W"
}
