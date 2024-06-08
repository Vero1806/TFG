package com.example.tfg.Interfaz.Ingresos

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta

class IngresosViewModel : ViewModel() {


    private val _cuentas = mutableStateListOf<Cuenta>()
    val cuentas: List<Cuenta> = _cuentas.toList()

    private val _listaNombresCuentas = mutableStateListOf<String>()
    val listaNombresCuentas: List<String> = _listaNombresCuentas.toList()

    private val _categorias = mutableStateListOf<Categoria>()
    val categorias: List<Categoria> = _categorias.toList()

    private val _listaNombresCategorias = mutableStateListOf<String>()
    val listaNombresCategorias: List<String> = _listaNombresCategorias.toList()

    private val _numeroIngresado = MutableLiveData<Double>()
    val numeroIngresado: LiveData<Double> get() = _numeroIngresado

    init {
        _numeroIngresado.value = 0.0

    }

    fun actualizarNumeroIngresado(numero: Double){
        _numeroIngresado.value = numero
    }
//    private fun obtenerCuentas(): List<Cuenta> {
//        _cuentas.addAll(tablaUsuario.obtenerCuentas())
//        actualizarListaNombresCuentas()
//        return cuentas
//    }
//
//    private fun actualizarListaNombresCuentas() {
//        _cuentas.forEach { cuentas ->
//            _listaNombresCuentas.add(cuentas.nombreCuenta)
//        }
//    }
//
//    private fun obtenerCategorias(): List<Categoria> {
//        _categorias.addAll(tablaUsuario.obtenerCategorias())
//        actualizarListaNombresCategorias()
//        return categorias
//    }
//
//    private fun actualizarListaNombresCategorias() {
//        _categorias.forEach { categorias ->
//            _listaNombresCategorias.add(categorias.nombreCategoria)
//        }
//    }
}
