package models

import models.Tables.TrolRow
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.Await

object RolFacade {
  private val tRolQuery: TableQuery[_root_.models.Tables.Trol] = Tables.Trol

  def insert(rol: Tables.TrolRow): Int = {
    Await.result(DB.db.run(tRolQuery += rol), Duration.Inf)
  }

  def getAll: Seq[TrolRow] = Await.result(DB.db.run(tRolQuery.result), Duration.Inf)

  def delete(rol: Tables.TrolRow): Int = {
    Await.result(DB.db.run(
      tRolQuery
        .filter(_.rolname === rol.rolname).delete
    ), Duration.Inf)
  }

  def update(rol: Tables.TrolRow): Int = {
    Await.result(DB.db.run(
      tRolQuery
        .filter(_.rolname === rol.rolname)
        .update(rol)
    ), Duration.Inf)
  }

  def getByName(name: String): _root_.models.Tables.TrolRow = {
    Await.result(DB.db.run(
      tRolQuery
        .filter(_.rolname === name)
        .result
    ), Duration.Inf).head
  }
}