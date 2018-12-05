package models

import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Future

object RolFacade {
  def insert(rol: Tables.TrolRow): Future[Int] = {
    DB.db.run(Tables.Trol += rol)
  }
}
