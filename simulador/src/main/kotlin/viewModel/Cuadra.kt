package viewModel

import freeFunctions.minimo
import model.DataCuadra
import rx.Observable
import rx.lang.kotlin.observable

/**
 * Created by CristianErik on 21/08/2016.
 */

class Cuadra(private val _dataCuadra: DataCuadra,  entryNode: ICruce) {

    var outgoingCrossingByCarsAmount = 0
    var outgoingTurningCarsAmount = 0
    var sendingCars: Observable<Cuadra>

    private var _incomingCarsAmount: Int = 0
        set(value) { field = minimo(_dataCuadra.capacidad / 2, value) }
    private val _incomingCarsAvailability: Int =  _dataCuadra.capacidad / 2 - _incomingCarsAmount
    private val _outgoingCarsAvailability: Int = _dataCuadra.capacidad / 2 - outgoingCrossingByCarsAmount - outgoingTurningCarsAmount

    private var timer: Observable<Int> =  observable { /*Do Something*/ }

    init {
        sendingCars = timer.doOnEach { moveCarsToTheFront() }.map { this }

        entryNode.horizontalOutgoingCars.subscribe { previousBlock ->
            if(previousBlock.isLeft()) {
                val block = previousBlock.left().get()
                val amount = minimo(_incomingCarsAvailability, block.outgoingCrossingByCarsAmount)
                _incomingCarsAmount += amount
                block.outgoingCrossingByCarsAmount -= amount
            } else if (previousBlock.isRight()) {
                val cuadra = previousBlock.right().get()
                val amount = minimo(_incomingCarsAvailability, cuadra.outgoingTurningCarsAmount)
                _incomingCarsAmount += amount
                cuadra.outgoingTurningCarsAmount -= amount
            }
        }
    }

    private fun moveCarsToTheFront() {
        val totalAmount: Int
        if (_incomingCarsAmount <= _outgoingCarsAvailability) {
            totalAmount = _incomingCarsAmount
            _incomingCarsAmount = 0
        } else {
            _incomingCarsAmount -= _outgoingCarsAvailability
            totalAmount = _outgoingCarsAvailability
        }
        val leftAmount = (totalAmount * 0.5).toInt()
        outgoingCrossingByCarsAmount += leftAmount
        outgoingTurningCarsAmount += totalAmount - leftAmount
    }

}
