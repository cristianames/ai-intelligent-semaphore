package main

import model.*
import viewModel.*
import java.util.*
import javax.xml.crypto.Data

/**
 * Created by CristianErik on 29/08/2016.
 */

data class StreetMap(val x: Int, val y: Int) {

    val terminalsHalfAmount = x + y

    val verticalBlocks = x * (y + 1)

    val horizontalBlocks = (x + 1) * y

    val semaphores = x * y

}

class Main(streetsMap: StreetMap) {

    val _semaphores: ArrayList<Semaforo>
    val _dataVerticalStreets: ArrayList<Calle>
    val _dataHorizontalStreets: ArrayList<Calle>

    init {
        _semaphores = createSemaphores(streetsMap.semaphores)
        _dataVerticalStreets = createVerticalStreets(streetsMap.x, streetsMap.y)
        _dataHorizontalStreets = createHorizontalStreets(streetsMap.x, streetsMap.y)
    }

    private fun createSemaphores(amount: Int): ArrayList<Semaforo> {
        val semaphores = arrayListOf<Semaforo>()
        for(i in 0..(amount - 1)) semaphores[i] = Semaforo(DataSemaforo(i))
        return semaphores
    }

    private fun createVerticalStreets(x: Int, y: Int): ArrayList<Calle> {
        val streets = ArrayList<Calle>(x)
        val blocks = ArrayList<Cuadra>(y + 1)
        for (i in 0..(x - 1)) {
            for (j in 0..y) {
                var entryNode: ICruce? = null
                when(i) {
                    0, 2, 4 -> {
                        if(j == y) entryNode = Entrada(DataEntrada(j * x + i))
                        else entryNode = _semaphores[j * x + i]
                        blocks[j] = CuadraVertical(DataCuadra(i * x + j, 100), entryNode)
                    }
                    else -> {
                        if(j == 0) entryNode = Entrada(DataEntrada(j * x + i))
                        else entryNode = _semaphores[(j - 1) * x + i]
                        blocks[j] = CuadraVertical(DataCuadra(i * x + j, 100), entryNode)
                    }
                }

            }
            var dataCalle: DataCalle? = null
            when(i) {
                0,2,4 -> dataCalle = DataCalle(DataCalle.Sentido.Norte)
                else -> dataCalle = DataCalle(DataCalle.Sentido.Sur)
            }
            streets[i] = Calle(dataCalle, blocks)
        }
        return streets
    }

    private fun createHorizontalStreets(x: Int, y: Int): ArrayList<Calle> {
        val streets = ArrayList<Calle>(y)
        val blocks = ArrayList<Cuadra>(x + 1)
        for (j in 0..(y - 1)) {
            for (i in 0..x) {
                var entryNode: ICruce? = null!!
                when(j) {
                    0, 3 -> {
                        if(i == x) entryNode = Entrada(DataEntrada(j * x + i))
                        else entryNode = _semaphores[j * x + i]
                        blocks[j] = CuadraVertical(DataCuadra(i * x + j, 100), entryNode)
                    }
                    else -> {
                        if(i == 0) entryNode = Entrada(DataEntrada(j * x + i))
                        else entryNode = _semaphores[j * ( x - 1) + i]
                        blocks[j] = CuadraVertical(DataCuadra(i * x + j, 100), entryNode)
                    }
                }

            }
            var dataCalle: DataCalle? = null
            when(j) {
                0,3 -> dataCalle = DataCalle(DataCalle.Sentido.Oeste)
                else -> dataCalle = DataCalle(DataCalle.Sentido.Este)
            }
            streets[j] = Calle(dataCalle, blocks)
        }
        return streets
    }

}