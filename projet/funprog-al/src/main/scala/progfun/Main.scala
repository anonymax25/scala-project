package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import play.api.libs.json._
import fr.esgi.al.funprog._
import better.files._

object Main extends App {

  val conf: Config = ConfigFactory.load()

  val inputLoader = new InputLoader(conf.getString("application.input-file"))
  val inputs = inputLoader.parseInput()
  val limit: Point = new Point(inputs._1, inputs._2)
  val mowers: List[Mower] = inputs._3

  val env =
    new Environnement(
      limit,
      mowers,
      conf.getString("application.is-env-verbose").toBoolean,
      conf.getString("application.turn-delay").toInt
    )

  val executed = Environnement.execute(env)
  val json = File(conf.getString("application.output-json-file"))

  json
    .createIfNotExists()
    .overwrite(
      Json.prettyPrint(Environnement.environnementWrites.writes(executed))
    )

  val csv = File(conf.getString("application.output-csv-file"))
  csv.createIfNotExists().overwrite(CSVExporter.export(executed.mowers))
}
