package extensions

import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

/**
 * Created by CristianErik on 24/08/2016.
 */


fun Polygon.setLoad(percentage: Double) {
    this.fill = Color(percentage, 1 - percentage, .0, 1.0)
}