package com.example.tfg.Interfaz.Cuentas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Cuenta

class CuentasViewModel : ViewModel() {

    private val _cuentas = MutableLiveData<List<Cuenta>>()
    val cuentas: LiveData<List<Cuenta>> get() = _cuentas

    private val _nombreNuevaCuenta = MutableLiveData<String>()
    val nombreNuevaCuenta: LiveData<String> get() = _nombreNuevaCuenta

    private val _limiteNuevaCuenta = MutableLiveData<Double>()
    val limiteNuevaCuenta: LiveData<Double> get() = _limiteNuevaCuenta

    init {
        _nombreNuevaCuenta.value = ""
        _limiteNuevaCuenta.value = 0.0
        valoresCuenta()
        comprobarNumeroDeCuentas()
    }

    fun valoresCuenta(){
        _cuentas.value = listOf(
            Cuenta(1, "Personal", 1000.0),
            Cuenta(2, "Ahorros", 2000.0),
            Cuenta(3, "Compartida", 3000.0)
        )
    }

    fun registrarNuevaCuenta(nombreNuevaCuenta: String, limiteNuevaCuenta: Double){
        _nombreNuevaCuenta.value = nombreNuevaCuenta
        _limiteNuevaCuenta.value = limiteNuevaCuenta
    }

    fun comprobarNumeroDeCuentas() : Boolean {
        val cuentas = _cuentas.value
        return if (cuentas != null && cuentas.size > 1 && cuentas.size < 4) {
            return true
        } else {
            false
        }
    }

}