package visualizador

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val controller = initializeView(primaryStage)

    }

    fun initializeView(primaryStage: Stage): Controller{
        val root = FXMLLoader.load<Parent>(javaClass.getResource("sample.fxml"))
        primaryStage.title = "Intelligent Semaphore"
        primaryStage.scene = Scene(root)
        primaryStage.show()
        return root as Controller
    }


}
