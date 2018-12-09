package views

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.event.ActionEvent
import scalafx.scene.input._
import scalafx.Includes._
import scalafx.scene.control.PasswordField

object Login extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    var user = ""
    var pass = ""
    title = "Log In"
    scene = new Scene(400, 250) {
      fill = Color.Bisque
      val buttonOK = new Button("OK") {
        prefWidth = 100
      }
      buttonOK.layoutX = 75
      buttonOK.layoutY = 200

      val buttonCancel = new Button("Cancel") {
        prefWidth = 100
      }
      buttonCancel.layoutX = 225
      buttonCancel.layoutY = 200

      val labelUser = new Label ("Usuario")
      labelUser.layoutX = 75
      labelUser.layoutY = 75

      val labelPass = new Label ("Contraseña")
      labelPass.layoutX = 75
      labelPass.layoutY = 125

      val textUser = new TextField(){
        prefWidth = 150
      }
      textUser.layoutX = 225
      textUser.layoutY = 75

      val textPass = new PasswordField(){
        prefWidth = 150
      }
      textPass.layoutX = 225
      textPass.layoutY = 125

      content = List(buttonOK, buttonCancel, labelUser, labelPass,textPass, textUser)

      buttonOK.onAction = (e:ActionEvent) => {
        user = textUser.text.value
        pass = textPass.text.value
        //teniendo user y pass como variables,
      }

      buttonCancel.onAction = (e:ActionEvent) => {
        //cerrar ventan

      }


    }
  }

}
