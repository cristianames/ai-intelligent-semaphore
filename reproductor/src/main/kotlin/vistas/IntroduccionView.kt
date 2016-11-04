package vistas

import javafx.scene.layout.AnchorPane
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.stage.Stage
import controladores.PrimaryStage

/**
 * Created by CristianErik on 26/10/2016.
 */
class IntroduccionView() : View() {

    @FXML var utnLogo: ImageView? = null
    @FXML var utnLabel: Label? = null
    @FXML var iaaLabel: Label? = null
    @FXML var newButton: Button? = null
    @FXML var loadButton: Button? = null

    companion object {

        fun Create(stage: Stage = PrimaryStage!!): IntroduccionView {
            val view = View.Create("introduccion", "Intelligent Semaphore", stage) as IntroduccionView
            return view
        }

    }

}