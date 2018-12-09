package views
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.collections.ObservableBuffer
import scalafx.beans.property._
import scalafx.scene.shape._
import scalafx.event.ActionEvent
import scalafx.scene.input._
import scalafx.Includes._
import scalafx.collections.ObservableBufferBase
import scalafx.scene.control.PasswordField

case class tPieza (id:Int, nombre:String, fabricante:String, id_tipo:String)

object Window extends JFXApp{
  stage = new JFXApp.PrimaryStage {
    val listaPiezas = List("si","La")
    title = "Piezas"
    scene = new Scene(750, 500) {
      fill = Color.MistyRose

      val labelMaterial = new Label("Materia")
      labelMaterial.layoutY = 50
      labelMaterial.layoutX = 50

      val lista = new ListView(listaPiezas)
      {
        prefHeight = 80
        prefWidth = 300

      }
      val data = ObservableBuffer(tPieza(1,"A","A","A"),tPieza(2,"B","B","B"))

      val tabla = new TableView(data)
      {
        prefHeight = 150
        prefWidth = 650
      }
        tabla.layoutY = 150
        tabla.layoutX = 50
      val colId = new TableColumn[tPieza,Int]("ID")

      colId.cellValueFactory = cdf => ObjectProperty(cdf.value.id)
      val colName = new TableColumn[tPieza,String]("Nombre")

      colName.cellValueFactory = cdf => StringProperty(cdf.value.nombre)
      val colFabricante = new TableColumn[tPieza,String]("Fabricante")

      colFabricante.cellValueFactory = cdf => StringProperty(cdf.value.fabricante)
      val colIdT = new TableColumn[tPieza,String]("ID_Tipo")

      colIdT.cellValueFactory = cdf => StringProperty(cdf.value.id_tipo)


      lista.layoutY = 50
      lista.layoutX = 225
      tabla.columns ++= List(colId, colName, colFabricante,colIdT)

      val labelNombre = new Label("Nombre")
      labelNombre.layoutY = 335
      labelNombre.layoutX = 32

      val labelFabricante = new Label("Fabricate")
      labelFabricante.layoutY = 375
      labelFabricante.layoutX = 32

      val txtNombre = new TextField()
      {
        prefWidth =600
      }

      txtNombre.layoutY=330
      txtNombre.layoutX = 100

      val txtFabricante = new TextField()
      {
        prefWidth =600
      }

      txtFabricante.layoutY=370
      txtFabricante.layoutX = 100

      val btnInsert = new Button("Insertar")
      {
        prefWidth= 75
      }

      btnInsert.layoutY = 425
      btnInsert.layoutX = 90

      val btnBorrar = new Button("Borrar")
      {
        prefWidth= 75
      }

      btnBorrar.layoutY = 425
      btnBorrar.layoutX = 255

      val btnActualizar = new Button("Actualizar")
      {
        prefWidth= 85
      }

      btnActualizar.layoutY = 425
      btnActualizar.layoutX = 420
      val btnSalir = new Button("Salir")
      {
        prefWidth= 75
      }

      btnSalir.layoutY = 425
      btnSalir.layoutX = 585


      content = List(labelMaterial, lista, tabla, labelNombre, labelFabricante, txtNombre, txtFabricante,
        btnInsert, btnBorrar,btnActualizar, btnSalir)

      btnInsert.onAction = (e:ActionEvent) => {
        //cuando usamos el boton
      }
      btnBorrar.onAction = (e:ActionEvent) => {
        //cuando usamos el boton
      }
      btnActualizar.onAction = (e:ActionEvent) => {
        //cuando usamos el boton
      }
      btnSalir.onAction = (e:ActionEvent) => {
        //cuando usamos el boton
      }

    }

  }

}
