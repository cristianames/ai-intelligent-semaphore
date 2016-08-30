package viewModel

import freeFunctions.minimo
import model.DataCuadra

class CuadraHorizontal(dataCuadra: DataCuadra, entryNode: ICruce)
         : Cuadra(dataCuadra){
    init {
        entryNode.crossingHorizontalOutgoingCars.subscribe { previousBlock ->
            val amount = minimo(_incomingCarsAvailability, previousBlock.outgoingCrossingByCarsAmount)
            _incomingCarsAmount += amount
            previousBlock.outgoingCrossingByCarsAmount -= amount
        }
        entryNode.turningHorizontalOutgoingCars.subscribe { previousBlock ->
            val amount = minimo(_incomingCarsAvailability, previousBlock.outgoingTurningCarsAmount)
            _incomingCarsAmount += amount
            previousBlock.outgoingTurningCarsAmount -= amount
        }
    }
}
