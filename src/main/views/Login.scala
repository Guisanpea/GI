package views

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.scene.shape._

object Login extends JFXApp {

  stage = new JFXApp.PrimaryStage {
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
      content = List(buttonOK, buttonCancel, labelUser)
    }
  }

}
