package controladores

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import modelos.DetalleCalle
import servicios.CorridasServicio
import vistas.IntroduccionView
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.swing.JFileChooser
import javax.swing.JOptionPane
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
        val filter = FileNameExtensionFilter("Archivos Json", "json")
        fileChooser.fileFilter = filter
        val status = fileChooser.showOpenDialog(null)
        // Si apretamos en aceptar ocurrirá esto
        if (status == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            if(!selectedFile.isJson()) {
                JOptionPane.showMessageDialog (null, "No se selecciono un archivo Json valido",
                        "Error al cargar archivo",
                        JOptionPane.ERROR_MESSAGE)
                return
            } else {
                val datos = loadFromFile(selectedFile.toPath())
                if (datos == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo cargar el archivo",
                            "Error al cargar archivo",
                            JOptionPane.ERROR_MESSAGE)
                    return
                }
                // Caso feliz
                if (JOptionPane.showConfirmDialog(null,
                        "Se cargo el archivo con exito. Ahora se procedera con las corridas",
                        "Carga finalizada",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE) == 0) {
                   val servicio = CorridasServicio(datos)
                    print(servicio.correrAlgoritmo(20))
                }
            }
        }



    }

    private fun loadFromFile(path: Path): DetalleCalle? {
        val mapper = ObjectMapper(JsonFactory()) // Enable Json parsing
        mapper.registerModule(KotlinModule()) // Enable Kotlin support

        return Files.newBufferedReader(path).use {
            mapper.readValue(it, DetalleCalle::class.java)
        }
    }

}

private fun File.isJson(): Boolean {
    val i = name.lastIndexOf('.')
    val extension = name.substring(i + 1).toLowerCase()
    return extension == "json"
}

