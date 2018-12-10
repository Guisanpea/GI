package views

import com.microsoft.sqlserver.jdbc._
import models.Tables.{TpiezasRow, TtipopiezaRow}
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.PasswordField
import models.{TipoPiezaFacade, _}

object App extends JFXApp {
  var userName: String = ""
  var password: String = ""
  loginScene()

  def loginScene(): Unit = {
    stage = new JFXApp.PrimaryStage {
      title = "Log In"
      scene = new Scene(400, 250) {
        fill = Color.MistyRose
        val label = new Label()
        label.layoutX = 100
        label.layoutY = 100

        val buttonOK: Button = new Button("OK") {
          prefWidth = 100
          layoutX = 75
          layoutY = 200
        }
        val buttonCancel: Button = new Button("Cancel") {
          prefWidth = 100
          layoutX = 225
          layoutY = 200
        }
        val labelUser: Label = new Label("Usuario") {
          layoutX = 75
          layoutY = 75
        }
        val labelPass: Label = new Label("Contraseña") {
          layoutX = 75
          layoutY = 125
        }
        val labelBien: Label = new Label("Bienvenido") {
          layoutX = 75
        }
        val textUser: TextField = new TextField() {
          prefWidth = 150
          layoutX = 225
          layoutY = 75
        }
        val textPass: PasswordField = new PasswordField() {
          prefWidth = 150
          layoutX = 225
          layoutY = 125
        }

        content = List(textUser, labelBien, textPass, buttonOK, buttonCancel, labelUser, labelPass)

        buttonOK.onAction = (e: ActionEvent) => {
          val databaseUser = UsuarioFacade.getByName(textUser.text.value)
          try {
            if (databaseUser.password == textPass.text.value) {
              userName = databaseUser.nombre
              password = databaseUser.password
              windowScene()
            }
          } catch {
            case uoe: UnsupportedOperationException => labelBien.text = "Usuario o contraseña incorrectos"
          }
        }

        buttonCancel.onAction = (e: ActionEvent) => {
          //cerrar ventana
          stage.close()
        }
      }
    }
  }


  def windowScene(): Unit = {
    val pieceTypeList: Seq[_root_.models.Tables.TtipopiezaRow] = TipoPiezaFacade.getAll
    stage = new JFXApp.PrimaryStage {
      title = "Piezas"

      scene = new Scene(750, 500) {
        def declareColumns(): Unit = {
          idCol.cellValueFactory = cdf => ObjectProperty(cdf.value.id)
          nameCol.cellValueFactory = cdf => StringProperty(cdf.value.nombre)
          manufacturerCol.cellValueFactory = cdf => StringProperty(cdf.value.fabricante)
          pieceTypeIdCol.cellValueFactory = cdf => StringProperty(cdf.value.idTipo)
        }

        def changeLabels(): Unit = {
          try {
            if (UsuarioFacade.getByName(userName).rolname != "invitado") {
              textFieldNombre.text = piecesTable.selectionModel.apply.getSelectedItem.nombre
              textFieldFabricante.text = piecesTable.selectionModel.apply.getSelectedItem.fabricante
            } else {
              errorLabel.text = "No tiene el rol necesario (actualmente es " + UsuarioFacade.getByName(userName).rolname + ")."
            }
          } catch {
            case npe: NullPointerException => errorLabel.text = "Seleccione un elemento de la tabla primero."
          }
        }

        def listPiecesOfPieceType(): Unit = {
          if (UsuarioFacade.getByName(userName).rolname != "invitado") {
            val idTipoPieza = pieceTypeListView.selectionModel.apply.getSelectedItem.idTipo //Nombre del elemento seleccionado de la lista
            val piecesOfPieceType = PiezaFacade.getAllByTipo(idTipoPieza)

            piecesTableData --= piecesTableData
            piecesTableData ++= ObservableBuffer(piecesOfPieceType)
          } else {
            errorLabel.text = "No tiene el rol necesario (actualmente es " + UsuarioFacade.getByName(userName).rolname + ")."
          }
        }

        def insertPiece(): Unit = {
          if (UsuarioFacade.getByName(userName).rolname == "administrador") {
            val pieceType = pieceTypeListView.selectionModel.value.getSelectedItem
            PiezaFacade.insert(Tables.TpiezasRow(0, textFieldNombre.text.value, textFieldFabricante.text.value, pieceType.idTipo))
            listPiecesOfPieceType()
          } else {
            errorLabel.text = "No tiene el rol necesario (actualmente es " + UsuarioFacade.getByName(userName).rolname + ")."
          }
        }

        def deletePiece(): Unit = {
          try {
            if (UsuarioFacade.getByName(userName).rolname == "administrador") {
              val piece = piecesTable.selectionModel.value.getSelectedItem
              PiezaFacade.delete(piece)
              listPiecesOfPieceType()
            } else {
              errorLabel.text = "No tiene el rol necesario (actualmente es " + UsuarioFacade.getByName(userName).rolname + ")."
            }
          } catch {
            case npe: NullPointerException => errorLabel.text = "Seleccione un elemento de la tabla primero."
          }
        }

        def updatePiece(): Unit = {
          try {
            if (UsuarioFacade.getByName(userName).rolname == "administrador") {
              val piece = piecesTable.selectionModel.value.getSelectedItem
              PiezaFacade.update(piece)
              listPiecesOfPieceType()
            } else {
              errorLabel.text = "No tiene el rol necesario (actualmente es " + UsuarioFacade.getByName(userName).rolname + ")."
            }
          } catch {
            case npe: NullPointerException => errorLabel.text = "Seleccione un elemento de la tabla primero."
          }
        }

        fill = Color.MistyRose

        val pieceList = Seq.empty[_root_.models.Tables.TpiezasRow]
        var piecesTableData = ObservableBuffer(pieceList)
        val idCol = new TableColumn[TpiezasRow, Int]("ID")
        val nameCol = new TableColumn[TpiezasRow, String]("Nombre")
        val manufacturerCol = new TableColumn[TpiezasRow, String]("Fabricante")
        val pieceTypeIdCol = new TableColumn[TpiezasRow, String]("ID_Tipo")
        declareColumns()

        val errorLabel: Label = new Label("Bienvenido") {
          layoutX = 50
          layoutY = 15
        }
        val materialLabel: Label = new Label("Materia") {
          layoutY = 50
          layoutX = 50
        }
        val pieceTypeListView: ListView[_root_.models.Tables.TtipopiezaRow] = new ListView(pieceTypeList) {
          prefHeight = 80
          prefWidth = 300
          layoutY = 50
          layoutX = 225
        }
        val piecesTable: TableView[_root_.models.Tables.TpiezasRow] = new TableView(piecesTableData) {
          prefHeight = 150
          prefWidth = 650
          layoutY = 150
          layoutX = 50
          columns ++= List(idCol, nameCol, manufacturerCol, pieceTypeIdCol)
        }
        val labelNombre: Label = new Label("Nombre") {
          layoutY = 335
          layoutX = 32
        }
        val manufacturerLabel: Label = new Label("Fabricante") {
          layoutY = 375
          layoutX = 32
        }
        val textFieldNombre: TextField = new TextField() {
          prefWidth = 600
          layoutY = 330
          layoutX = 100
        }
        val textFieldFabricante: TextField = new TextField() {
          prefWidth = 600
          layoutY = 370
          layoutX = 100
        }
        val insertButton: Button = new Button("Insertar") {
          layoutY = 425
          layoutX = 90
          prefWidth = 75
        }
        val deleteButton: Button = new Button("Borrar") {
          prefWidth = 75
          layoutY = 425
          layoutX = 255
        }
        val updateButton: Button = new Button("Actualizar") {
          prefWidth = 85
          layoutY = 425
          layoutX = 420
        }
        val exitButton: Button = new Button("Salir") {
          prefWidth = 75
          onAction = (a: ActionEvent) => {
            loginScene()
          }
          layoutY = 425
          layoutX = 585
        }

        piecesTable.selectionModel.apply.selectedItems.onChange {
          changeLabels()
        }

        pieceTypeListView.selectionModel.apply.selectedItems.onChange {
          listPiecesOfPieceType()
        }

        insertButton.onAction = (a: ActionEvent) => {
          insertPiece()
        }

        deleteButton.onAction = (a: ActionEvent) => {
          deletePiece()
        }

        updateButton.onAction = (a: ActionEvent) => {
          updatePiece()
        }

        content = List(errorLabel, materialLabel, pieceTypeListView, piecesTable, labelNombre, manufacturerLabel, textFieldNombre, textFieldFabricante, insertButton, deleteButton, updateButton, exitButton)
      }
    }
  }
}