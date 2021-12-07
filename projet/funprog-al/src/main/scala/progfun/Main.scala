package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}

import fr.esgi.al.funprog._

final case class DonneesIncorectesException(
    val message: String
) extends Exception(message)

object Main extends App {

  val conf: Config = ConfigFactory.load()
  val inputFilePath: String = conf.getString("application.input-file")

  val loader = new InputLoader(inputFilePath)

  val inputs = loader.parseInput()

  val x: Int = inputs._1
  val y: Int = inputs._2
  val mowers: List[Mower] = inputs._3

  val env = new Environnement(x, y, mowers)

  println(env.mowers)

  while (env.mowers.filter(m => m.actions.length > 0).length > 0) {
    env.play()
    env.display()
    Thread.sleep(250)
  }

  println("End of all mower sequences, writing result file...")

  println(env.mowers)
}
