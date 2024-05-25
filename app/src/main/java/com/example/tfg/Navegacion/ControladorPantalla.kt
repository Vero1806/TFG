package com.example.tfg.Navegacion

//Referencia https://www.youtube.com/watch?v=eNuaMn4ukdo

sealed class ControladorPantalla(val ruta: String) {
    object Login: ControladorPantalla("Inicio")
    object Registro: ControladorPantalla("Registro")
}
