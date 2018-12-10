package models

import models.Tables.TpiezasRow
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object PiezaFacade {
  private var tPiezaQuery = Tables.Tpiezas

  def insert(piezas: Tables.TpiezasRow): Int = {
    Await.result(DB.db.run(tPiezaQuery += piezas), Duration.Inf)
  }

  def getAll: Seq[TpiezasRow] = Await.result(DB.db.run(tPiezaQuery.result), Duration.Inf)

  def getAllByTipo(idTipo: String) = {
    Await.result(DB.db.run(
      tPiezaQuery
        .filter(_.idTipo === idTipo)
        .result
    ), Duration.Inf)
  }

  def delete(piezas: Tables.TpiezasRow): Int = {
    Await.result(DB.db.run(
      tPiezaQuery
        .filter(_.id === piezas.id).delete
    ), Duration.Inf)
  }

  def update(piezas: Tables.TpiezasRow): Int = {
    Await.result(DB.db.run(
      sqlu"""UPDATE tPiezas
          SET NOMBRE = ${piezas.nombre}, FABRICANTE=${piezas.fabricante}
          WHERE ID = ${piezas.id}
        """
    ), Duration.Inf)
  }

  def getById(id: Int): _root_.models.Tables.TpiezasRow = {
    Await.result(DB.db.run(
      tPiezaQuery
        .filter(_.id === id)
        .result
    ), Duration.Inf).head
  }
}