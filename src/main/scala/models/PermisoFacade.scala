package models

import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Future

object PermisoFacade {
  def insert(permiso: Tables.TpermisoRow): Future[Int] = {
    DB.db.run(Tables.Tpermiso += permiso)
  }
}
