package model

/**
 * Created by CristianErik on 21/08/2016.
 */

data class DataCuadra( private val id: Int,
                       private val calle: DataCalle,
                       private val longitud: Int) {

    var capacidad: Int =  (longitud * 0.5).toInt()
}