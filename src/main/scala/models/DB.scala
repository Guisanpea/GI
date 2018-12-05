package models

import com.microsoft.sqlserver.jdbc.SQLServerDriver
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object DB {
  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  val db = dbConfig.db
}
