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
            private val _horizontalOutgoingCars: Cuadra): ICruce {

    override val horizontalOutgoingCars = observable<Either<Cuadra, Cuadra>> { subscriber ->
        _verticalIncomingCars.sendingCars.subscribe { block ->
            subscriber.onNext(Either.Right(block))
        }
        _horizontalOutgoingCars.sendingCars.subscribe { block ->
            subscriber.onNext(Either.Left(block))
        }
    }


    override val verticalOutgoingCars: Observable<Either<Cuadra, Cuadra>> = observable<Either<Cuadra, Cuadra>> { subscriber ->
        _verticalIncomingCars.sendingCars.subscribe { block ->
            subscriber.onNext(Either.Left(block))
        }
        _horizontalOutgoingCars.sendingCars.subscribe { block ->
            subscriber.onNext(Either.Right(block))
        }
    }

}