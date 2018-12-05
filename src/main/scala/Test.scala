import models.Tables.TrolRow
import models.{PermisoFacade, RolFacade, Tables}
import scalafx.util.Duration

import scala.concurrent.{Await, duration}

object Test extends App {
  val newElem = TrolRow("Illo", Some("nombre"), admin = true)
  val eventualInsertResult = RolFacade.insert(newElem)
  val insertResult = Await.result(eventualInsertResult, duration.Duration.Inf)
  println(s"Insert result: $insertResult\n")
}
