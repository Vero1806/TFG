@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Categorias
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.R

//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriasScreen(estadoNavegacion: NavController, categoriasViewModel: CategoriasViewModel = viewModel()) {

    val categoriasCuenta1 by categoriasViewModel.categorias.collectAsState()
    val cuentas by categoriasViewModel.cuentas.collectAsState()

    var estadoExpansionCategoria = rememberSaveable { mutableStateOf(false) }
    var seleccionarCuenta = rememberSaveable { mutableStateOf(cuentas.firstOrNull()?.nombreCuenta ?: "") }

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) }
    ){ innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TituloCategorias()
                    Spacer(modifier = Modifier.width(16.dp))
                    Logo()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TituloCambiarCuentas()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCuentas(estadoExpansionCategorias = estadoExpansionCategoria,
                        nombresCategoria = cuentas.map { it.nombreCuenta },
                        seleccionarCategoria = seleccionarCuenta)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                botonRecargar(estadoNavegacion = estadoNavegacion)
                Spacer(modifier = Modifier.padding(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    nombreCategoria()
                    Spacer(modifier = Modifier.padding(5.dp))
                    limiteCategoria()
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    listaNombresCategorias(categoriasViewModel.obtenerCategoriasPorCuenta(1))
                    Spacer(modifier = Modifier.padding(5.dp))
                    listaLimitesCategorias(categoriasViewModel.obtenerCategoriasPorCuenta(1))
                }
//                Row {
//                    BotonCancelar(estadoNavegacion = estadoNavegacion)
//                    Spacer(modifier = Modifier.width(10.dp))
//                    BotonRegistrarse()
//                    BotonCancelar(estadoNavegacion = estadoNavegacion)
//                    Spacer(modifier = Modifier.width(10.dp))
//                    BotonRegistrarse()
//                }
            }
        }
    }
}

@Composable
fun Logo() {
    Box(modifier = Modifier
        .size(80.dp, 80.dp)
        .padding(8.dp)) {
        if(isSystemInDarkTheme()){
            Image(
                painter = painterResource(id = R.drawable.logo_dark_mode),
                contentDescription = "Logo",
                Modifier.size(250.dp)
            )
        }else {
            Image(
                painter = painterResource(id = R.drawable.logo_light_mode),
                contentDescription = "Logo",
                Modifier.size(250.dp)
            )
        }
    }
}
@Composable
fun TituloCategorias() {
    Text(text = "Categorias", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}



@Composable
fun TituloCambiarCuentas(){
    Text(text = "Cambiar a otra cuenta:", fontSize = 15.sp)
}

@Composable
fun desplegableCuentas(
    estadoExpansionCategorias: MutableState<Boolean>,
    nombresCategoria: List<String>,
    seleccionarCategoria: MutableState<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Button(onClick = { estadoExpansionCategorias.value = true }) {
                Text(text = seleccionarCategoria.value)
            }
            if (estadoExpansionCategorias.value) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize()
                ) {
                    Column {
                        nombresCategoria.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        seleccionarCategoria.value = item
                                        estadoExpansionCategorias.value = false
                                    }
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = item,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.surface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun botonRecargar(estadoNavegacion: NavController) {
    Button(onClick = { estadoNavegacion.navigate("Categorias") },
        modifier = Modifier
            .width(135.dp)
            .height(65.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .padding(5.dp)
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cambiar")
                Text(text = "Cuenta")
            }
        }
    }
}

@Composable
fun nombreCategoria(){
    Text(text = "Nombre:", fontSize = 15.sp)
}

@Composable
fun limiteCategoria(){
    Text(text = "Limite", fontSize = 15.sp)
}

@Composable
fun listaNombresCategorias(listaCategorias: List<Categoria>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        listaCategorias.forEach { categoria ->
            Text(
                text = categoria.nombreCategoria,
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun listaLimitesCategorias(listaCategorias: List<Categoria>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        listaCategorias.forEach { categoria ->
            Text(
                text = categoria.cantidadLimite.toString(),
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun NavigacionIferior(modifier: Modifier = Modifier, estadoNavegacion: NavController) {
    // Implement composable here
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ){

        NavigationBarItem(
            selected = false,
            onClick = {estadoNavegacion.navigate("Perfil")},
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text (text = "Perfil") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Ingresos") },
            icon = { Icon(painter = painterResource(id = R.drawable.more), contentDescription = null) },
            label = { Text (text = "Ingreso") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Cuentas") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_credit_card_24), contentDescription = null) },
            label = { Text (text = "Cuentas") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Gastos") },
            icon = { Icon(painter = painterResource(id = R.drawable.less), contentDescription = null) },
            label = { Text (text = "Gastos") }
        )
        NavigationBarItem(
            selected = true,
            onClick = {  },
            icon = { Icon(painter = painterResource(id = R.drawable.category), contentDescription = null) },
            label = { Text (text = "Categorias") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPerfil(){
    val estadoNavegacion = rememberNavController()
    CategoriasScreen(estadoNavegacion)
}