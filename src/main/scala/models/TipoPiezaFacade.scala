package models

import models.Tables.TtipopiezaRow
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.Await

object TipoPiezaFacade {
  private val tTipopiezaQuery = Tables.Ttipopieza

  def insert(tipoPieza: Tables.TtipopiezaRow): Int = {
    Await.result(DB.db.run(tTipopiezaQuery += tipoPieza), Duration.Inf)
  }

  def getAll: Seq[TtipopiezaRow] = Await.result(DB.db.run(tTipopiezaQuery.result), Duration.Inf)

  def delete(tipoPieza: Tables.TtipopiezaRow): Int = {
    Await.result(DB.db.run(
      tTipopiezaQuery
        .filter(_.idTipo === tipoPieza.idTipo).delete
    ), Duration.Inf)
  }

  def update(tipoPieza: Tables.TtipopiezaRow): Int = {
    Await.result(DB.db.run(
      tTipopiezaQuery
        .filter(_.idTipo === tipoPieza.idTipo)
        .update(tipoPieza)
    ), Duration.Inf)
  }

  def getById(id: String): _root_.models.Tables.TtipopiezaRow = {
    Await.result(DB.db.run(
      tTipopiezaQuery
        .filter(_.idTipo === id )
        .result
    ), Duration.Inf).head
  }
}
