package viewModel

import model.DataSemaforo
import rx.Observable

/**
 * Created by CristianErik on 21/08/2016.
 */

class Semaforo(private val _dataSemaforo: DataSemaforo): ICruce {

    override val crossingHorizontalOutgoingCars: Observable<Cuadra> = null!!

    override val turningHorizontalOutgoingCars: Observable<Cuadra> = null!!

    override val crossingVerticalOutgoingCars: Observable<Cuadra> = null!!

    override val turningVerticalOutgoingCars: Observable<Cuadra> = null!!

}