package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import play.api.libs.json._
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

  while (env.mowers.filter(m => m.playedActions.length > 0).length > 0) {
    env.play()
    // print("\u001b[2J")
    // Thread.sleep(250)
  }

  env.display()

  // println(env.mowers)

  println(Json.prettyPrint(env.toJson))

}
