package com.example.tfg.BBDD.Objetos

import java.time.LocalDateTime

data class Transaccion(
    var idTransaccion: Int,
    var idCuenta: Int,
    var idCategoria: Int,
    var cantidadTransaccion: Float,
    var conceptoTransaccion: String = "",
    //la fecha se pasará en formato LocalDataTime desde el ViewModel.
    // Cuidado con esto vamos a tener que buscar formas de hacerlo
    //val fechaTransaccion: LocalDateTime
)
