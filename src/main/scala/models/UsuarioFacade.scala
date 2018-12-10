package models

import models.Tables.TusuarioRow
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.Await

object UsuarioFacade {
  private val tUsuarioQuery: TableQuery[_root_.models.Tables.Tusuario] = Tables.Tusuario

  def insert(usuario: Tables.TusuarioRow): Int = {
    Await.result(DB.db.run(tUsuarioQuery += usuario), Duration.Inf)
  }

  def getAll: Seq[TusuarioRow] = Await.result(DB.db.run(tUsuarioQuery.result), Duration.Inf)

  def delete(usuario: Tables.TusuarioRow): Int = {
    Await.result(DB.db.run(
      tUsuarioQuery
        .filter(_.nombre === usuario.nombre).delete
    ), Duration.Inf)
  }

  def update(usuario: Tables.TusuarioRow): Int = {
    Await.result(DB.db.run(
      tUsuarioQuery
        .filter(_.nombre === usuario.nombre)
        .update(usuario)
    ), Duration.Inf)
  }

  def getByName(name: String): _root_.models.Tables.TusuarioRow = {
    Await.result(DB.db.run(
      tUsuarioQuery
        .filter(_.nombre === name)
        .result
    ), Duration.Inf).head
  }
}

