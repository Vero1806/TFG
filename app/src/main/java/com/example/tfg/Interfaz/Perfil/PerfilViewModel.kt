package com.example.tfg.Interfaz.Perfil

import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta
import com.example.tfg.BBDD.Objetos.Transaccion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> get() = _categorias

    private val _cuentas = MutableStateFlow<List<Cuenta>>(emptyList())
    val cuentas: StateFlow<List<Cuenta>> get() = _cuentas

    init {
        obtenerListaDeTransacciones()
        obtenerListaDeCategorias()
        obtenerListaDeCuentas()
    }

    fun obtenerListaDeTransacciones(): List<Transaccion> {
        return listOf(
            Transaccion(idTransaccion = 1, idCuenta = 1, idCategoria = 1, cantidadTransaccion = 150.0f, conceptoTransaccion = "Salario"),
            Transaccion(idTransaccion = 2, idCuenta = 1, idCategoria = 2, cantidadTransaccion = -50.0f, conceptoTransaccion = "Compra en supermercado"),
            Transaccion(idTransaccion = 3, idCuenta = 2, idCategoria = 1, cantidadTransaccion = 200.0f, conceptoTransaccion = "Venta de producto"),
            Transaccion(idTransaccion = 4, idCuenta = 2, idCategoria = 3, cantidadTransaccion = -100.0f, conceptoTransaccion = "Pago de alquiler"),
            Transaccion(idTransaccion = 5, idCuenta = 2, idCategoria = 2, cantidadTransaccion = -25.0f, conceptoTransaccion = "Cena en restaurante"),
            Transaccion(idTransaccion = 6, idCuenta = 2, idCategoria = 4, cantidadTransaccion = 75.0f, conceptoTransaccion = "Devolución de impuestos"),
            Transaccion(idTransaccion = 7, idCuenta = 1, idCategoria = 3, cantidadTransaccion = -30.0f, conceptoTransaccion = "Pago de servicios"),
            Transaccion(idTransaccion = 8, idCuenta = 1, idCategoria = 1, cantidadTransaccion = 120.0f, conceptoTransaccion = "Ingresos varios"),
            Transaccion(idTransaccion = 9, idCuenta = 1, idCategoria = 4, cantidadTransaccion = -70.0f, conceptoTransaccion = "Compra de ropa"),
            Transaccion(idTransaccion = 10, idCuenta = 2, idCategoria = 2, cantidadTransaccion = -40.0f, conceptoTransaccion = "Gastos de transporte")
        )
    }

    private fun obtenerListaDeCategorias() {
        val todasLasCategorias = listOf(
            Categoria(idCategoria = 1, idCuenta = 1, nombreCategoria = "Alimentos", cantidadLimite = 500.0f),
            Categoria(idCategoria = 2, idCuenta = 1, nombreCategoria = "Transporte", cantidadLimite = 300.0f),
            Categoria(idCategoria = 3, idCuenta = 1, nombreCategoria = "Ocio", cantidadLimite = 200.0f),
            Categoria(idCategoria = 4, idCuenta = 1, nombreCategoria = "Salud", cantidadLimite = 400.0f),
            Categoria(idCategoria = 5, idCuenta = 2, nombreCategoria = "Transporte", cantidadLimite = 300.0f),
            Categoria(idCategoria = 6, idCuenta = 2, nombreCategoria = "Educación", cantidadLimite = 600.0f),
            Categoria(idCategoria = 7, idCuenta = 2, nombreCategoria = "Viajes", cantidadLimite = 800.0f),
            Categoria(idCategoria = 8, idCuenta = 2, nombreCategoria = "Ahorros", cantidadLimite = 1000.0f)
        )
        _categorias.value = todasLasCategorias
    }

    private fun obtenerListaDeCuentas() {
        val todasLasCuentas = listOf(
            Cuenta(1, "Personal", 1000.0),
            Cuenta(2, "Ahorros", 2000.0),
            Cuenta(3, "Compartida", 3000.0)
        )
        _cuentas.value = todasLasCuentas
    }

    fun obtenerCategoriasPorCuenta(idCuenta: Int): List<Categoria> {
        val categoriasPorCuenta = mutableListOf<Categoria>()
        for (categoria in _categorias.value) {
            if (categoria.idCuenta == idCuenta) {
                categoriasPorCuenta.add(categoria)
            }
        }
        return categoriasPorCuenta
    }
    fun obtenerLimitesPorCategoria(idCuenta: Int): Map<String, Float> {
        val limitesPorCategoria = mutableMapOf<String, Float>()
        val categorias = obtenerCategoriasPorCuenta(idCuenta)

        for (categoria in categorias) {
            limitesPorCategoria[categoria.nombreCategoria] = categoria.cantidadLimite
        }

        return limitesPorCategoria
    }
    fun obtenerTransaccionesPorCategorias(idCuenta: Int): Map<String, Float> {
        val transaccionesPorCategoriaSumadas = mutableMapOf<String, Float>()
        val transacciones = obtenerListaDeTransacciones().filter { it.idCuenta == idCuenta }

        for (transaccion in transacciones) {
            val nombreCategoria = _categorias.value.find { it.idCategoria == transaccion.idCategoria }?.nombreCategoria
            if (nombreCategoria != null) {
                transaccionesPorCategoriaSumadas[nombreCategoria] =
                    (transaccionesPorCategoriaSumadas[nombreCategoria] ?: 0.0f) + transaccion.cantidadTransaccion
            }
        }

        return transaccionesPorCategoriaSumadas
    }

}