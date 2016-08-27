package viewModel

import org.funktionale.either.Either
import rx.Observable

/**
 * Created by CristianErik on 22/08/2016.
 */

interface ICruce {

    val horizontalOutgoingCars: Observable<Either<Cuadra, Cuadra>>

    val verticalOutgoingCars: Observable<Either<Cuadra, Cuadra>>

}
//
//enum class EDecisionAutos {
//    DOBLAN, CRUZAN;
//
//    var cuadra: Cuadra? = null
//
//    companion object {
//        fun Doblan(cuadra: Cuadra) {
//
//        }
//    }
//}