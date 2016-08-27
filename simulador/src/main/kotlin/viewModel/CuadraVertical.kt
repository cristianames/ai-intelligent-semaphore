package viewModel

import freeFunctions.minimo
import model.DataCuadra

class CuadraVertical(dataCuadra: DataCuadra, entryNode: ICruce)
: Cuadra(dataCuadra, entryNode) {
    init {
        entryNode.crossingVerticalOutgoingCars.subscribe { previousBlock ->
            val amount = minimo(_incomingCarsAvailability, previousBlock.outgoingCrossingByCarsAmount)
            _incomingCarsAmount += amount
            previousBlock.outgoingCrossingByCarsAmount -= amount
        }
        entryNode.turningVerticalOutgoingCars.subscribe { previousBlock ->
            val amount = minimo(_incomingCarsAvailability, previousBlock.outgoingTurningCarsAmount)
            _incomingCarsAmount += amount
            previousBlock.outgoingTurningCarsAmount -= amount
        }
    }
}