package main

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import vistas.Introduction

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

        val opener = {
            Introduction.Create("UTN", "TGC", { Introduction() }, {  })
        }

        val controller = Introduction.Create("UTN", "IAA", opener, {  })
        controller.show()
    }


}
