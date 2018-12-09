import models.Tables.TpiezasRow
import models.{PermisoFacade, RolFacade, Tables}
import scalafx.util.Duration

import scala.concurrent.{Await, duration}

object Test extends App {
  // Creo una nueva fila para la base de datos
  val rol = Tables.TrolRow("Illoo", Some("nombre"), admin = true)
  println(s"${Console.GREEN}Elemento a insertar:" +
    s"${Console.RED}$rol\n")

  // Borro de la base de datos el elemento
  RolFacade.delete(rol)

  // Realizo la inserción en la base de datos y compruebo el resultado
  val insertResult = RolFacade.insert(rol)
  println(s"${Console.CYAN}Resultado de la inserción: $insertResult\n")

  //modifico el elemento de la base de datos
  val modifiedRol = rol.copy(admin = false)
  RolFacade.update(modifiedRol)
  // Ahora lo busco en la Base de datos y lo imprimo por pantalla
  println(s"${Console.RED}${RolFacade.getByName(rol.rolname)}")

  // Obtengo todos los roles e imprimo por pantalla
  println(s"${Console.GREEN}Lista de elementos:")
  RolFacade.getAll.foreach(println(_))
}
