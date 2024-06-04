package com.example.tfg.Interfaz.Ingresos

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta
import com.example.tfg.BBDD.Tablas.TablaUsuario

class IngresosViewModel : ViewModel() {
    private val tablaUsuario = TablaUsuario()

    private val _cuentas = mutableStateListOf<Cuenta>()
    val cuentas: List<Cuenta> = _cuentas.toList()

    private val _listaNombresCuentas = mutableStateListOf<String>()
    val listaNombresCuentas: List<String> = _listaNombresCuentas.toList()

    private val _categorias = mutableStateListOf<Categoria>()
    val categorias: List<Categoria> = _categorias.toList()


    init {
        obtenerCuentas()
        obtenerCategorias()
    }

    private fun obtenerCuentas() {
        _cuentas.clear()
        _cuentas.addAll(tablaUsuario.obtenerCuentas())
        actualizarListaNombresCuentas()
    }

    private fun actualizarListaNombresCuentas() {
        _listaNombresCuentas.clear()
        _listaNombresCuentas.addAll(_cuentas.map { it.nombreCuenta })
    }
    private fun obtenerCategorias() {
        _categorias.clear()
        _categorias.addAll(tablaUsuario.obtenerCategorias())
    }
}
