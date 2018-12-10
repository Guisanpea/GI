package models

import slick.ast.ColumnOption.AutoInc

/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.SQLServerProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Tpermiso.schema ++ Tpiezas.schema ++ Trol.schema ++ Ttipopieza.schema ++ Tusuario.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Tpermiso
    *  @param rolname Database column rolName SqlType(varchar), Length(50,true)
    *  @param pantalla Database column pantalla SqlType(varchar), Length(50,true)
    *  @param acceso Database column acceso SqlType(bit)
    *  @param modificacion Database column modificacion SqlType(bit) */
  case class TpermisoRow(rolname: String, pantalla: String, acceso: Boolean, modificacion: Boolean)

  /** GetResult implicit for fetching TpermisoRow objects using plain SQL queries */
  implicit def GetResultTpermisoRow(implicit e0: GR[String], e1: GR[Boolean]): GR[TpermisoRow] = GR{
    prs => import prs._
      TpermisoRow.tupled((<<[String], <<[String], <<[Boolean], <<[Boolean]))
  }
  /** Table description of table tPermiso. Objects of this class serve as prototypes for rows in queries. */
  class Tpermiso(_tableTag: Tag) extends profile.api.Table[TpermisoRow](_tableTag, Some("dbo"), "tPermiso") {
    def * = (rolname, pantalla, acceso, modificacion) <> (TpermisoRow.tupled, TpermisoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(rolname), Rep.Some(pantalla), Rep.Some(acceso), Rep.Some(modificacion)).shaped.<>({r=>import r._; _1.map(_=> TpermisoRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column rolName SqlType(varchar), Length(50,true) */
    val rolname: Rep[String] = column[String]("rolName", O.Length(50,varying=true))
    /** Database column pantalla SqlType(varchar), Length(50,true) */
    val pantalla: Rep[String] = column[String]("pantalla", O.Length(50,varying=true))
    /** Database column acceso SqlType(bit) */
    val acceso: Rep[Boolean] = column[Boolean]("acceso")
    /** Database column modificacion SqlType(bit) */
    val modificacion: Rep[Boolean] = column[Boolean]("modificacion")

    /** Primary key of Tpermiso (database name PK_tPermiso) */
    val pk = primaryKey("PK_tPermiso", (rolname, pantalla))

    /** Foreign key referencing Trol (database name FK_tPermiso_tRol) */
    lazy val trolFk = foreignKey("FK_tPermiso_tRol", rolname, Trol)(r => r.rolname, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Tpermiso */
  lazy val Tpermiso = new TableQuery(tag => new Tpermiso(tag))

  /** Entity class storing rows of table Tpiezas
    *  @param id Database column ID SqlType(int identity), PrimaryKey
    *  @param nombre Database column NOMBRE SqlType(varchar), Length(255,true)
    *  @param fabricante Database column FABRICANTE SqlType(varchar), Length(255,true)
    *  @param idTipo Database column ID_TIPO SqlType(varchar), Length(4,true) */
  case class TpiezasRow(id: Int, nombre: String, fabricante: String, idTipo: String)
  /** GetResult implicit for fetching TpiezasRow objects using plain SQL queries */
  implicit def GetResultTpiezasRow(implicit e0: GR[Int], e1: GR[String]): GR[TpiezasRow] = GR{
    prs => import prs._
      TpiezasRow.tupled((<<[Int], <<[String], <<[String], <<[String]))
  }
  /** Table description of table tPiezas. Objects of this class serve as prototypes for rows in queries. */
  class Tpiezas(_tableTag: Tag) extends profile.api.Table[TpiezasRow](_tableTag, Some("dbo"), "tPiezas") {
    def * = (id, nombre, fabricante, idTipo) <> (TpiezasRow.tupled, TpiezasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(nombre), Rep.Some(fabricante), Rep.Some(idTipo)).shaped.<>({r=>import r._; _1.map(_=> TpiezasRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(int identity), PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    /** Database column NOMBRE SqlType(varchar), Length(255,true) */
    val nombre: Rep[String] = column[String]("NOMBRE", O.Length(255,varying=true))
    /** Database column FABRICANTE SqlType(varchar), Length(255,true) */
    val fabricante: Rep[String] = column[String]("FABRICANTE", O.Length(255,varying=true))
    /** Database column ID_TIPO SqlType(varchar), Length(4,true) */
    val idTipo: Rep[String] = column[String]("ID_TIPO", O.Length(4,varying=true))

    /** Foreign key referencing Ttipopieza (database name FK_tPiezas_tTipoPieza) */
    lazy val ttipopiezaFk = foreignKey("FK_tPiezas_tTipoPieza", idTipo, Ttipopieza)(r => r.idTipo, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Tpiezas */
  lazy val Tpiezas = new TableQuery(tag => new Tpiezas(tag))

  /** Entity class storing rows of table Trol
    *  @param rolname Database column rolName SqlType(varchar), PrimaryKey, Length(50,true)
    *  @param roldes Database column rolDes SqlType(varchar), Length(255,true)
    *  @param admin Database column admin SqlType(bit) */
  case class TrolRow(rolname: String, roldes: Option[String], admin: Boolean)
  /** GetResult implicit for fetching TrolRow objects using plain SQL queries */
  implicit def GetResultTrolRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Boolean]): GR[TrolRow] = GR{
    prs => import prs._
      TrolRow.tupled((<<[String], <<?[String], <<[Boolean]))
  }
  /** Table description of table tRol. Objects of this class serve as prototypes for rows in queries. */
  class Trol(_tableTag: Tag) extends profile.api.Table[TrolRow](_tableTag, Some("dbo"), "tRol") {
    def * = (rolname, roldes, admin) <> (TrolRow.tupled, TrolRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(rolname), roldes, Rep.Some(admin)).shaped.<>({r=>import r._; _1.map(_=> TrolRow.tupled((_1.get, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column rolName SqlType(varchar), PrimaryKey, Length(50,true) */
    val rolname: Rep[String] = column[String]("rolName", O.PrimaryKey, O.Length(50,varying=true))
    /** Database column rolDes SqlType(varchar), Length(255,true) */
    val roldes: Rep[Option[String]] = column[Option[String]]("rolDes", O.Length(255,varying=true))
    /** Database column admin SqlType(bit) */
    val admin: Rep[Boolean] = column[Boolean]("admin")
  }
  /** Collection-like TableQuery object for table Trol */
  lazy val Trol = new TableQuery(tag => new Trol(tag))

  /** Entity class storing rows of table Ttipopieza
    *  @param idTipo Database column ID_TIPO SqlType(varchar), PrimaryKey, Length(4,true)
    *  @param nombre Database column NOMBRE SqlType(varchar), Length(80,true) */
  case class TtipopiezaRow(idTipo: String, nombre: String) {
    override def toString: String = nombre
  }
  /** GetResult implicit for fetching TtipopiezaRow objects using plain SQL queries */
  implicit def GetResultTtipopiezaRow(implicit e0: GR[String]): GR[TtipopiezaRow] = GR{
    prs => import prs._
      TtipopiezaRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table tTipoPieza. Objects of this class serve as prototypes for rows in queries. */
  class Ttipopieza(_tableTag: Tag) extends profile.api.Table[TtipopiezaRow](_tableTag, Some("dbo"), "tTipoPieza") {
    def * = (idTipo, nombre) <> (TtipopiezaRow.tupled, TtipopiezaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idTipo), Rep.Some(nombre)).shaped.<>({r=>import r._; _1.map(_=> TtipopiezaRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID_TIPO SqlType(varchar), PrimaryKey, Length(4,true) */
    val idTipo: Rep[String] = column[String]("ID_TIPO", O.PrimaryKey, O.Length(4,varying=true))
    /** Database column NOMBRE SqlType(varchar), Length(80,true) */
    val nombre: Rep[String] = column[String]("NOMBRE", O.Length(80,varying=true))
  }
  /** Collection-like TableQuery object for table Ttipopieza */
  lazy val Ttipopieza = new TableQuery(tag => new Ttipopieza(tag))

  /** Entity class storing rows of table Tusuario
    *  @param nombre Database column nombre SqlType(varchar), PrimaryKey, Length(50,true)
    *  @param password Database column password SqlType(varchar), Length(50,true)
    *  @param rolname Database column rolName SqlType(varchar), Length(50,true) */
  case class TusuarioRow(nombre: String, password: String, rolname: String)
  /** GetResult implicit for fetching TusuarioRow objects using plain SQL queries */
  implicit def GetResultTusuarioRow(implicit e0: GR[String]): GR[TusuarioRow] = GR{
    prs => import prs._
      TusuarioRow.tupled((<<[String], <<[String], <<[String]))
  }
  /** Table description of table tUsuario. Objects of this class serve as prototypes for rows in queries. */
  class Tusuario(_tableTag: Tag) extends profile.api.Table[TusuarioRow](_tableTag, Some("dbo"), "tUsuario") {
    def * = (nombre, password, rolname) <> (TusuarioRow.tupled, TusuarioRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(nombre), Rep.Some(password), Rep.Some(rolname)).shaped.<>({r=>import r._; _1.map(_=> TusuarioRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column nombre SqlType(varchar), PrimaryKey, Length(50,true) */
    val nombre: Rep[String] = column[String]("nombre", O.PrimaryKey, O.Length(50,varying=true))
    /** Database column password SqlType(varchar), Length(50,true) */
    val password: Rep[String] = column[String]("password", O.Length(50,varying=true))
    /** Database column rolName SqlType(varchar), Length(50,true) */
    val rolname: Rep[String] = column[String]("rolName", O.Length(50,varying=true))

    /** Foreign key referencing Trol (database name FK_tUsuario_tRol) */
    lazy val trolFk = foreignKey("FK_tUsuario_tRol", rolname, Trol)(r => r.rolname, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Tusuario */
  lazy val Tusuario = new TableQuery(tag => new Tusuario(tag))
}
