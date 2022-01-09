package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import play.api.libs.json._
import fr.esgi.al.funprog._

final case class DonneesIncorectesException(
    val message: String
) extends Exception(message)

object Main extends App {

  val conf: Config = ConfigFactory.load()

  val inputLoader = new InputLoader(conf.getString("application.input-file"))

  val inputs = inputLoader.parseInput()

  val x: Int = inputs._1
  val y: Int = inputs._2
  val mowers: List[Mower] = inputs._3

  val env =
    new Environnement(
      x,
      y,
      mowers,
      conf.getString("application.turn-delay").toInt
    )

  val executed = Environnement.execute(env)

  // println(Json.prettyPrint(executed.toJson))

  println(CSVExporter.export(executed.mowers))
}
