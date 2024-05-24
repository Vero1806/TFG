package com.example.tfg.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tfg.BBDD.Objetos.CategoriaGenerica
import com.example.tfg.BBDD.Tablas.TablaCategoriaGenerica
import com.example.tfg.Login.ui.LoginViewModel

//class Visual(TablaCategoriaGenerica) {
    @Composable
    fun Visual(tablaCategoriaGenerica: TablaCategoriaGenerica) {

        Box (
            Modifier
                .fillMaxSize()
                .padding(20.dp)) {

            ImprimirCategoriasGenericas(tablaCategoriaGenerica)
        }
    }

@Composable
fun ImprimirCategoriasGenericas(tablaCategoriaGenerica: TablaCategoriaGenerica) {
    // Estado para mantener la lista de categorías
    val categoriasGenericas = remember { mutableStateOf(listOf<CategoriaGenerica>()) }

    // Efecto lateral para cargar las categorías cuando se inicia el Composable
    LaunchedEffect(Unit) {
        categoriasGenericas.value = tablaCategoriaGenerica.obtenerCategoriasGenerica()
    }

    // UI para mostrar la lista de categorías
    LazyColumn {
        items(categoriasGenericas.value) { categoria ->
            CategoriaGenericaItem(categoria)
        }
    }
}

@Composable
fun CategoriaGenericaItem(categoria: CategoriaGenerica) {
    Text(
        text = "ID: ${categoria.idCategoria}, Nombre: ${categoria.nombreCategoria}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

class TablaCategoriaGenericaPrueba : TablaCategoriaGenerica(null) {
    override fun obtenerCategoriasGenerica(): List<CategoriaGenerica> {
        return (1..10).map { id ->
            CategoriaGenerica(idCategoria = id, nombreCategoria = "Categoría $id")
        }
    }
}

// Función de vista previa para ver el Composable
@Preview(showBackground = true)
@Composable
fun PreviewVisual() {
    Visual(tablaCategoriaGenerica = TablaCategoriaGenericaPrueba())
}
//}