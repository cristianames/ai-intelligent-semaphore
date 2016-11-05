package controladores

import vistas.IntroduccionView
import javax.swing.JFileChooser

/**
 * Created by CristianErik on 04/11/2016.
 */

class IntroduccionController : Controller() {
    override val _view: IntroduccionView

    init {
        _view = IntroduccionView.Create()
        initializeView()
    }

    fun initializeView() {
        _view.iaaLabel?.text = "Inteligencia Artificial Avanzada"
        _view.utnLabel?.text = "UTN FRBA"
//        _view.utnLogo?.image = TODO: Agregar una imagen
        _view.newButton?.setOnAction { newButtonAction() }
        _view.newButton?.isDisable = true
        _view.loadButton?.setOnAction { loadButtonAction() }
    }

    fun newButtonAction() {
        print("Aun no se implementa")
    }

    fun loadButtonAction() {
        val fileChooser = JFileChooser(".")
        val status = fileChooser.showOpenDialog(null)
        // Si apretamos en aceptar ocurrirá esto
        if (status == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            System.out.println(selectedFile)
            // Si apretamos en cancelar o cerramos la ventana ocurrirá esto
        } else if (status == JFileChooser.CANCEL_OPTION) {
            println("Cancelar")
        }
    }

}