package viewModel

import model.DataCruce
import org.funktionale.either.Either
import rx.Observable
import rx.lang.kotlin.observable
import rx.lang.kotlin.subscriber

/**
 * Created by CristianErik on 21/08/2016.
 */

class Cruce(private val _dataCruce: DataCruce,
            private val _verticalIncomingCars: Cuadra,
            private val _horizontalIncomingCars: Cuadra): ICruce {

    override val crossingHorizontalOutgoingCars = observable<Cuadra> { subscriber ->
        _horizontalIncomingCars.sendingCars.subscribe { subscriber.onNext(it) }
    }

    override val turningHorizontalOutgoingCars = observable<Cuadra> { subscriber ->
        _verticalIncomingCars.sendingCars.subscribe { subscriber.onNext(it) }
    }

    override val crossingVerticalOutgoingCars = observable<Cuadra> { subscriber ->
        _verticalIncomingCars.sendingCars.subscribe { subscriber.onNext(it) }
    }

    override val turningVerticalOutgoingCars = observable<Cuadra> { subscriber ->
        _horizontalIncomingCars.sendingCars.subscribe { subscriber.onNext(it) }
    }

}