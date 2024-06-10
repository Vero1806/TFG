package com.example.tfg.Interfaz.AgregarCategoria
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AgregarCategoriasViewModel : ViewModel() {

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> get() = _categorias

    private val _cuentas = MutableStateFlow<List<Cuenta>>(emptyList())
    val cuentas: StateFlow<List<Cuenta>> get() = _cuentas

    private val _nombreNuevaCategoria = MutableLiveData<String>()
    val nombreNuevaCategoria: LiveData<String> get() = _nombreNuevaCategoria

    init {
        obtenerListaDeCuentas()
        obtenerListaDeCategorias()
    }

    private fun obtenerListaDeCuentas() {
        val todasLasCuentas = listOf(
            Cuenta(1, "Personal", 1000.0),
            Cuenta(2, "Ahorros", 2000.0),
            Cuenta(3, "Compartida", 3000.0)
        )
        _cuentas.value = todasLasCuentas
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
    fun comprobarNombreCategoriaPorCuenta(nombreNuevaCategoria: String, idCuenta: Int): Boolean {
        for (categoria in _categorias.value) {
            if (categoria.idCuenta == idCuenta && categoria.nombreCategoria == nombreNuevaCategoria) {
                return false
            }
        }
        return true
    }



    fun actualizarNombreNuevaCuenta(nombreNuevaCuenta: String) {
        _nombreNuevaCategoria.value = nombreNuevaCuenta
    }

}