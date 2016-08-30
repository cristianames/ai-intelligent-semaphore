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

    val _semaphores: Array<Semaforo>
    val _verticalBlocks: Array<Cuadra>
    val _horizontalBlocks: Array<Cuadra>
    val _entryBlocks: Array<Entrada>
    val _exitBlocks: Array<Salida>
    val _dataVerticalStreets: Array<DataCalle>
    val _dataHorizontalStreets: Array<DataCalle>

    init {
        _semaphores = createSemaphores(streetsMap.semaphores)
        _entryBlocks = createEntryTerminals(streetsMap.terminalsHalfAmount)
        _exitBlocks = createExitTerminals(streetsMap.terminalsHalfAmount)
        _verticalBlocks = createVerticalBlocks(streetsMap.verticalBlocks)
        _horizontalBlocks = createHorizontalBlocks(streetsMap.horizontalBlocks)
        _dataVerticalStreets = createVerticalStreets(streetsMap.x, streetsMap.y)
        _dataHorizontalStreets = createHorizontalStreets(streetsMap.x, streetsMap.y)

    }

    private fun createSemaphores(amount: Int): Array<Semaforo> {
        return Array(amount) { Semaforo(DataSemaforo(it)) }
    }

    private fun createEntryTerminals(amount: Int): Array<Entrada> {
        return Array(amount) { Entrada(DataEntrada(it)) }
    }

    private fun createExitTerminals(amount: Int): Array<Salida> {
        return Array(amount) { Salida(DataSalida(it)) }
    }

    private fun createVerticalBlocks(amount: Int): Array<Cuadra> {
        return Array(amount) { Cuadra(DataCuadra(it, DataCalle(it), 100 ), entryNode = FindTerminalNodeByPosition(it)) }
    }

    private fun createHorizontalBlocks(amount: Int): Array<Cuadra> {
        return Array(amount) { Cuadra(DataCuadra(it, DataCalle(it), 100 ), entryNode = FindTerminalNodeByPosition(it)) }
    }

    private fun createVerticalStreets(x: Int, y: Int): ArrayList<Calle> {

        val streets = ArrayList<Calle>(x)
        val blocks = ArrayList<Cuadra>(x + 1)
        val semaphores = ArrayList<Semaforo>(x)
        for (i in 0..x) {
            for (j in 0..y) {
                blocks[j] = _horizontalBlocks[j * x + i]
                _semaphores[j * x + i])
            }
            blocks[x + 1] = _horizontalBlocks[(y + 1) * x + x]
            var dataCalle: DataCalle? = null
            when(i) {
                0,2,4 -> dataCalle = DataCalle(DataCalle.Sentido.Norte)
                else -> dataCalle = DataCalle(DataCalle.Sentido.Sur)
            }
            streets[i] = Calle(dataCalle, blocks, semaphores)
        }
        return streets

    }

    private fun createHorizontalStreets(x: Int, y: Int): ArrayList<Calle> {
        val streets = ArrayList<Calle>(x)
        val blocks = ArrayList<Cuadra>(x + 1)
        val semaphores = ArrayList<Semaforo>(x)
        for (j in 0..y) {
            for (i in 0..x) {
                blocks[i] = _horizontalBlocks[j * x + i]
                _semaphores[j * x + i])
            }
            blocks[x + 1] = _horizontalBlocks[j * x + x + 1]
            var dataCalle: DataCalle? = null
            when(j) {
                0,3 -> dataCalle = DataCalle(DataCalle.Sentido.Oeste)
                else -> dataCalle = DataCalle(DataCalle.Sentido.Este)
            }
            streets[j] = Calle(dataCalle, blocks, semaphores)
        }
        return streets
    }

    private fun FindTerminalNodeByPosition(index: Int): ICruce {
        return Semaforo(_dataSemaforo = DataSemaforo(index)) //Make a real calculation
    }
}