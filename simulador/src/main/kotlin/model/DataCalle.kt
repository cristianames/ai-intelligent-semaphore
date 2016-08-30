package model

/**
 * Created by CristianErik on 22/08/2016.
 */

data class DataCalle(val sentido: Sentido) {

    enum class Sentido {
        Norte, Sur, Este, Oeste;
    }

}