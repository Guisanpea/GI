package models

import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object PermisoFacade {
  private val tPermisoQuery: TableQuery[_root_.models.Tables.Tpermiso] = Tables.Tpermiso

  def insert(permiso: Tables.TpermisoRow): Int = {
    Await.result(DB.db.run( tPermisoQuery += permiso), Duration.Inf)
  }

  def getAll: Seq[_root_.models.Tables.TpermisoRow] = Await.result(DB.db.run(tPermisoQuery.result), Duration.Inf)

  def delete(permiso: Tables.TpermisoRow): Int = {
    Await.result(DB.db.run(
      tPermisoQuery
        .filter(_.rolname === permiso.rolname)
        .filter(_.pantalla === permiso.pantalla)
        .delete
    ), Duration.Inf)
  }

  def update(permiso: Tables.TpermisoRow): Int = {
    Await.result(DB.db.run(
      tPermisoQuery
        .filter(_.rolname === permiso.rolname)
        .filter(_.pantalla === permiso.pantalla)
        .update(permiso)
    ), Duration.Inf)
  }
}
