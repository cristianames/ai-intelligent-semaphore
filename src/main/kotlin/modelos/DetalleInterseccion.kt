package modelos

/**
 * Created by CristianErik on 05/11/2016.
 */

data class DetalleInterseccion(val cantPorSegundo: Int, val empalmaPorDerecha: Boolean) {

    fun print() {
        print("${this.javaClass.name}{ cantPorSegundo: $cantPorSegundo; EmpalmaPorDerecha: $empalmaPorDerecha }")
    }

}