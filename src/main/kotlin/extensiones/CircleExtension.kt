package extensiones

import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle

/**
 * Created by CristianErik on 24/08/2016.
 */

enum class Direction {
    Vertical, Horizontal;

    companion object {
        fun makeRandom(value: Double): Direction {
            if(value < 0.5) return Horizontal
            else return Vertical
        }
    }
}

fun Circle.setDirection(direction: Direction) {
    when (direction) {
        Direction.Vertical -> this.fill = Color.BLUEVIOLET
        Direction.Horizontal -> this.fill = Color.ROSYBROWN
    }
}

