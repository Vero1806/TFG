@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Perfil
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import com.example.tfg.R



//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(estadoNavegacion: NavController, perfilViewModel: PerfilViewModel = viewModel()) {
    val cuentas by perfilViewModel.cuentas.collectAsState()

    var estadoExpansionCuenta = rememberSaveable { mutableStateOf(false) }
    var seleccionarCuenta = rememberSaveable { mutableStateOf(cuentas.firstOrNull()?.nombreCuenta ?: "") }

    val cuentaSeleccionada = cuentas.find { it.nombreCuenta == seleccionarCuenta.value }
    val categorias = cuentaSeleccionada?.let { perfilViewModel.obtenerCategoriasPorCuenta(it.idCuenta) } ?: emptyList()

    val transaccionesPorCategorias = cuentaSeleccionada?.let {
        perfilViewModel.obtenerTransaccionesPorCategorias(it.idCuenta) } ?: emptyMap()

    val limitesPorCategoria = cuentaSeleccionada?.let {
        perfilViewModel.obtenerLimitesPorCategoria(it.idCuenta) } ?: emptyMap()

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) }
    ){ innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .horizontalScroll(rememberScrollState())
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    tituloPerfilScreen()
                    Spacer(modifier = Modifier.width(16.dp))
                    Logo()
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TituloCambiarCuentas()
                    Spacer(modifier = Modifier.width(8.dp))
                    desplegableCuentas(estadoExpansionCuenta = estadoExpansionCuenta,
                        nombresCuenta = cuentas.map { it.nombreCuenta },
                        seleccionarCuenta = seleccionarCuenta)
                }

                Spacer(modifier = Modifier.padding(10.dp))

//                MostrarCuentaSeleccionadaYLimite(
//                    cuentaSeleccionada = cuentaSeleccionada,
//                    seleccionarCuenta = seleccionarCuenta,
//                    perfilViewModel = perfilViewModel)

                Spacer(modifier = Modifier.padding(15.dp))

                Row{
                    TituloCategorias()
                    Spacer(modifier = Modifier.width(15.dp))
                    TituloGastos()
                    Spacer(modifier = Modifier.width(15.dp))
                    TituloLimites()
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Row {
                    ColumnaNombresCuentas(transaccionesPorCategorias = transaccionesPorCategorias)
                    Spacer(modifier = Modifier.width(5.dp))
                    ColumnaGastoPorCuenta(transaccionesPorCategorias = transaccionesPorCategorias)
                    Spacer(modifier = Modifier.width(5.dp))
                    ColumnaLimitesCuentas(
                        transaccionesPorCategorias = transaccionesPorCategorias,
                        limitesPorCategoria = limitesPorCategoria)
                }
                Spacer(modifier = Modifier.padding(15.dp))

                Column (modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End) {
                    BotonCerrarSesion(estadoNavegacion = estadoNavegacion)
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

//Función del Logo
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
fun tituloPerfilScreen(){
    Box(modifier = Modifier
        .padding(start = 15.dp)) {
        Text(text = "Perfil", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TituloCambiarCuentas(){
    Box(modifier = Modifier
        .padding(start = 10.dp)) {
        Text(text = "Cambiar a otra cuenta:", fontSize = 15.sp)
    }
}
@Composable
fun desplegableCuentas(
    estadoExpansionCuenta: MutableState<Boolean>,
    nombresCuenta: List<String>,
    seleccionarCuenta: MutableState<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Button(onClick = { estadoExpansionCuenta.value = true }) {
                Text(text = seleccionarCuenta.value)
            }
            if (estadoExpansionCuenta.value) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize()
                ) {
                    Column {
                        nombresCuenta.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        seleccionarCuenta.value = item
                                        estadoExpansionCuenta.value = false
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
//
//@Composable
//fun MostrarCuentaSeleccionadaYLimite(
//    cuentaSeleccionada: Cuenta?,
//    seleccionarCuenta: MutableState<String>,
//    perfilViewModel: PerfilViewModel
//) {
//    val categoriaSeleccionada = cuentaSeleccionada?.let { cuenta ->
//        seleccionarCuenta.value.takeIf { it.isNotEmpty() }?.let { nombreCuenta ->
//            perfilViewModel.obtenerCategoriasPorCuenta(cuenta.idCuenta).find { it.nombreCategoria == nombreCuenta }
//        }
//    }
//
//    Text(
//        text = "Cuenta: ${categoriaSeleccionada?.nombreCategoria ?: " "}    Límite: ${categoriaSeleccionada?.cantidadLimite ?: " "}",
//        fontSize = 16.sp
//    )
//}

@Composable
fun TituloCategorias(){
    Text(text = "Categorías", fontSize = 20.sp)
}

@Composable
fun TituloGastos(){
    Text(text = "Gatos", fontSize = 20.sp)
}
@Composable
fun TituloLimites(){
    Text(text = "Límites", fontSize = 20.sp)
}

@Composable
fun ColumnaNombresCuentas(transaccionesPorCategorias: Map<String, Float>,){
    Column(modifier = Modifier.padding(16.dp)) {
        transaccionesPorCategorias.forEach { (categoria, cantidad) ->
            Text(
                text = "$categoria: ",
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
@Composable
fun ColumnaGastoPorCuenta(
    transaccionesPorCategorias: Map<String, Float>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        transaccionesPorCategorias.forEach { (categoria, cantidad) ->
            Text(
                text = "$cantidad ",
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}


@Composable
fun ColumnaLimitesCuentas(
    transaccionesPorCategorias: Map<String, Float>,
    limitesPorCategoria: Map<String, Float>
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        transaccionesPorCategorias.forEach { (categoria, cantidad) ->
            val cantidadLimite = limitesPorCategoria[categoria] ?: 0.0f
            Text(
                text = " $cantidadLimite",
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}


@Composable
fun BotonCerrarSesion(estadoNavegacion: NavController) {
    Button(
        onClick = {
            estadoNavegacion.navigate("login")
        },
        modifier = Modifier
            .width(105.dp)
            .height(65.dp),
    ){
        Box {
            Column (
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Cerrar")
                Text(text = "Sesión")
            }
        }
    }
}

//Referencia práctica 3 de Jose Enrique
@Composable
private fun NavigacionIferior(modifier: Modifier = Modifier, estadoNavegacion: NavController) {
    // Implement composable here
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ){

        NavigationBarItem(
            selected = true,
            onClick = {  },
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
            selected = false,
            onClick = { estadoNavegacion.navigate("Categorias") },
            icon = { Icon(painter = painterResource(id = R.drawable.category), contentDescription = null) },
            label = { Text (text = "Categorias") }
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPerfil(){
    val estadoNavegacion = rememberNavController()
    PerfilScreen(estadoNavegacion)
}