package controladores

import javafx.application.Application
import javafx.stage.Stage

var PrimaryStage: Stage? = null

class Main : Application() {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        PrimaryStage = primaryStage

        val controller = IntroduccionController()
        controller.pushView()
    }


}
