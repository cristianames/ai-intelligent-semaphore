package modelos

/**
 * Created by CristianErik on 05/11/2016.
 */

class DetalleInterseccion(val cantPorSegundo: Int, val color: String) {

    fun print() {
        print("${this.javaClass.name}{ cantPorSegundo: $cantPorSegundo; color: $color }")
    }

}