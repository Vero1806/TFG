package com.example.tfg.Interfaz.Gastos

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta

class GastosViewModel : ViewModel() {

    private val _cuentas = mutableStateListOf<Cuenta>()
    val cuentas: List<Cuenta> = _cuentas.toList()

    private val _listaNombresCuentas = mutableStateListOf<String>()
    val listaNombresCuentas: List<String> = _listaNombresCuentas.toList()

    private val _categorias = mutableStateListOf<Categoria>()
    val categorias: List<Categoria> = _categorias.toList()

    private val _listaNombresCategorias = mutableStateListOf<String>()
    val listaNombresCategorias: List<String> = _listaNombresCategorias.toList()

}