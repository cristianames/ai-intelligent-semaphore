package viewModel

import model.DataEntrada
import rx.Observable

/**
 * Created by CristianErik on 21/08/2016.
 */

class Entrada(private val _dataEntraa: DataEntrada): ICruce {

    override val crossingHorizontalOutgoingCars: Observable<Cuadra> = null!!

    override val turningHorizontalOutgoingCars: Observable<Cuadra> = null!!

    override val crossingVerticalOutgoingCars: Observable<Cuadra> = null!!

    override val turningVerticalOutgoingCars: Observable<Cuadra> = null!!

}