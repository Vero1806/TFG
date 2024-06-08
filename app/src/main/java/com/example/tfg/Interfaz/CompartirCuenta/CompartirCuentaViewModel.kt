package com.example.tfg.Interfaz.CompartirCuenta

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompartirCuentaViewModel: ViewModel() {

    private val _emailCompartirCuenta = MutableLiveData<String>()
    val emailCompartirCuenta: LiveData<String> get() = _emailCompartirCuenta

    init {
        _emailCompartirCuenta.value = ""
    }

    fun actualizarNombreNuevaCuenta(nombreNuevaCuenta: String) {
        _emailCompartirCuenta.value = nombreNuevaCuenta
    }

    private fun emailCompatirValido(emailCompartirCuenta: String): Boolean
    = Patterns.EMAIL_ADDRESS.matcher(emailCompartirCuenta).matches()
}