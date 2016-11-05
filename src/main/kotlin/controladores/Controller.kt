package controladores

import vistas.View

/**
 * Created by CristianErik on 04/11/2016.
 */

abstract class Controller {

    abstract protected val _view: View

    fun pushView() {
        _view.show()
    }

    fun popView() {
        throw UnsupportedOperationException()
    }

}