package controladores

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import modelos.DetalleInterseccion
import servicios.YAMLFiltro
import vistas.IntroduccionView
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

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
        val filter = FileNameExtensionFilter("Archivos YAML", "yaml")
        fileChooser.fileFilter = filter
        val status = fileChooser.showOpenDialog(null)
        // Si apretamos en aceptar ocurrirá esto
        if (status == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            if(selectedFile.isYAML()) {
                val interseccion = loadFromFile(selectedFile.toPath())
                if (interseccion == null) {
                    // TODO: Deberia lanzarse un popup diciendo que no se pudo cargar el archivo
                    print("SOY UN POPUP")
                } else {
                    interseccion.print()
                }

            } else {
                // TODO: Deberia lanzarse un popup diciendo que se selecciono un archivo invalido
                print("SOY UN POPUP")
            }

            // Si apretamos en cancelar o cerramos la ventana ocurrirá esto
        } else if (status == JFileChooser.CANCEL_OPTION) {
            println("Cancelar")
        }



    }

    private fun loadFromFile(path: Path): DetalleInterseccion? {
        val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
        mapper.registerModule(KotlinModule()) // Enable Kotlin support

        return Files.newBufferedReader(path).use {
            mapper.readValue(it, DetalleInterseccion::class.java)
        }
    }

}

private fun File.isYAML(): Boolean {
    val i = name.lastIndexOf('.')
    val extension = name.substring(i + 1).toLowerCase()
    return extension == "yaml"
}

