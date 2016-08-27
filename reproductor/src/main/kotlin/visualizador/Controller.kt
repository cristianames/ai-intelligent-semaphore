package visualizador

import extensions.Direction
import extensions.setDirection
import extensions.setLoad
import javafx.fxml.FXML
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Circle
import javafx.scene.shape.Polygon
import java.util.concurrent.ThreadLocalRandom

class Controller : AnchorPane() {

    var verticalStreetStatus = Array(30, { 0.5 })
    var horizontalStreetStatus = Array(30, { 0.5 })
    var semaphoresStatus = Array(25, { Direction.Horizontal })

    @FXML var streetsPane: AnchorPane? = null

    @FXML var semaphores: AnchorPane? = null

    @FXML var horizontalBlocks: AnchorPane? = null

    @FXML var verticalBlocks: AnchorPane? = null

    init {
        kotlin.concurrent.timer(initialDelay = 2000, period = 500, action = { reloadBlocks() })
        kotlin.concurrent.timer(initialDelay = 5000, period = 400, action = { changeBlocksStatus() })
        kotlin.concurrent.timer(initialDelay = 4000, period = 5000, action = { changesSemaphoresStatus() })
    }

    @FXML
    fun doSomething() {
//        println("The button was clicked!")
//        val street = streetsPane?.children?.get(0) as Pane
//        street.style = "-fx-background-color: #FFFFFF;"
//
//        semaphores?.children?.forEach { (it as Circle).setDirection(Direction.Horizontal)  }
//
//        horizontalBlocks?.children?.forEach {
//            val value = ThreadLocalRandom.current().nextDouble(.0,1.0)
//            println(value)
//            (it as AnchorPane).children?.forEach { (it as Polygon).setLoad(value) }
//        }
    }

    private fun reloadBlocks() {
        for(j in 0..4) {
            for (i in 0..5) {
                ((verticalBlocks?.children?.get(j) as AnchorPane).children?.get(i) as Polygon)
                        .setLoad(verticalStreetStatus[j * 6 + i])
                ((horizontalBlocks?.children?.get(j) as AnchorPane).children?.get(i) as Polygon)
                        .setLoad(horizontalStreetStatus[j * 6 + i])
            }
        }
        for(i in 0..24) {
            (semaphores?.children?.get(i) as Circle).setDirection(semaphoresStatus[i])
        }
    }

    private fun changeBlocksStatus() {
        for(i in 0..29) {
            if(ThreadLocalRandom.current().nextDouble(.0,1.0) > 0.7)
                verticalStreetStatus[i] = ThreadLocalRandom.current().nextDouble(.0,1.0)
        }
        for(i in 0..29) {
            if(ThreadLocalRandom.current().nextDouble(.0,1.0) > 0.7)
                horizontalStreetStatus[i] = ThreadLocalRandom.current().nextDouble(.0,1.0)
        }
    }

    private fun changesSemaphoresStatus() {
        for(i in 0..24) {
            if(ThreadLocalRandom.current().nextDouble(.0,1.0) > 0.85)
                semaphoresStatus[i] = Direction.makeRandom(ThreadLocalRandom.current().nextDouble(.0,1.0))
        }
    }
}

data class BlockStateColor(val row: Int, val column: Int, val load: Double)

//    init {
//        val fxmlLoader = FXMLLoader(javaClass.getResource(
//                "sample.fxml"))
//        fxmlLoader.setRoot(this)
//        fxmlLoader.setController(this)
//
//        try {
//            fxmlLoader.load<Any>()
//        } catch (exception: IOException) {
//            throw RuntimeException(exception)
//        }
//
//    }