package com.example.tfg.BBDD.Objetos

data class Cuenta (
    var idCuenta: Int,
    var nombreCuenta: String = "",
    var limiteTotal: Float
)
