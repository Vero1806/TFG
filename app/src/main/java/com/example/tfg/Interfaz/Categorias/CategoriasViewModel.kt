package com.example.tfg.Interfaz.Categorias
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoriasViewModel : ViewModel() {

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> get() = _categorias

    private val _cuentas = MutableStateFlow<List<Cuenta>>(emptyList())
    val cuentas: StateFlow<List<Cuenta>> get() = _cuentas

    init {
        obtenerListaDeCategorias()
        obtenerListaDeCuentas()
    }

    private fun obtenerListaDeCategorias() {
        val todasLasCategorias = listOf(
            Categoria(idCategoria = 1, idCuenta = 1, nombreCategoria = "Alimentos", cantidadLimite = 500.0f),
            Categoria(idCategoria = 2, idCuenta = 1, nombreCategoria = "Transporte", cantidadLimite = 300.0f),
            Categoria(idCategoria = 3, idCuenta = 1, nombreCategoria = "Entretenimiento", cantidadLimite = 200.0f),
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
        return _categorias.value.filter { it.idCuenta == idCuenta }
    }
}
