package vistas

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.scene.layout.AnchorPane
import kotlin.properties.Delegates

/**
 * Created by CristianErik on 01/11/2016.
 */

abstract class View: AnchorPane() {
    companion object {

        fun Create(fileName: String, windowTitle: String? = null, stage: Stage): View {
            val fxmlLoader = FXMLLoader()
            val root = fxmlLoader.load<Parent>(javaClass.getResource("$fileName.fxml").openStream())
            val view = fxmlLoader.getController<View>()

            if(windowTitle != null) stage.title = windowTitle
            stage.scene = Scene(root)
            view.stage = stage
            return view
        }

    }

    var stage: Stage by Delegates.notNull<Stage>()

    fun show() = stage.show()

}