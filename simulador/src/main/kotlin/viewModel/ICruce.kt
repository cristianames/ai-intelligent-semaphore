package viewModel

import org.funktionale.either.Either
import rx.Observable

/**
 * Created by CristianErik on 22/08/2016.
 */

interface ICruce {

    val crossingHorizontalOutgoingCars: Observable<Cuadra>

    val turningHorizontalOutgoingCars: Observable<Cuadra>

    val crossingVerticalOutgoingCars: Observable<Cuadra>

    val turningVerticalOutgoingCars: Observable<Cuadra>

}