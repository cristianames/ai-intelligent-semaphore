package viewModel

import freeFunctions.minimo
import model.DataCuadra

class CuadraHorizontal(dataCuadra: DataCuadra, entryNode: ICruce)
         : Cuadra(dataCuadra, entryNode){
    init {
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
}
