@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.ModificarLimite
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.tfg.Interfaz.Ingresos.IngresosViewModel
import com.example.tfg.R

//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificarLimiteScreen(estadoNavegacion: NavController, modificarLimiteViewModel: ModificarLimiteViewModel = viewModel()) {
    val textoCalculadora = rememberSaveable { mutableStateOf("") }
    val cuentas by modificarLimiteViewModel.cuentas.collectAsState()
    val categoriasPorCuenta by modificarLimiteViewModel.categoriasPorCuenta.collectAsState()

    var estadoExpansionCuenta = rememberSaveable { mutableStateOf(false) }
    var seleccionarCuenta = rememberSaveable { mutableStateOf(cuentas.firstOrNull()?.nombreCuenta ?: "") }
    var estadoExpansionCategoria = rememberSaveable { mutableStateOf(false) }
    var seleccionarCategoria = rememberSaveable { mutableStateOf("") }


    val cuentaSeleccionada = cuentas.find { it.nombreCuenta == seleccionarCuenta.value }
    //uso de corrutinas para actualizar el estado de las categorias por cuenta seleccionada
    LaunchedEffect(cuentaSeleccionada) {
        cuentaSeleccionada?.let {
            modificarLimiteViewModel.seleccionarCuentaPorId(it.idCuenta)
        }
    }

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    tituloModificarLimiteScreen()
                    Spacer(modifier = Modifier.width(16.dp))
                    Logo()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    tituloCuentas()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCuentas(
                        estadoExpansionCuenta = estadoExpansionCuenta,
                        nombresCuenta = cuentas.map { it.nombreCuenta },
                        seleccionarCuenta = seleccionarCuenta
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    tituloCategoria()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCategorias(
                        estadoExpansionCategoria = estadoExpansionCategoria,
                        nombresCategoria = categoriasPorCuenta.map { it.nombreCategoria },
                        seleccionarCategoria = seleccionarCategoria
                    )
                }
                Spacer(modifier = Modifier.padding(15.dp))
                tituloCalculadora()
                Spacer(modifier = Modifier.padding(10.dp))
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally){
                    cuadradoTextoCalculadoraIngreso(textoCalculadora.value)
                    Spacer(modifier = Modifier.padding(5.dp))
                    botonesCalculadoraIngreso(textoCalculadora)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                botonNuevoLimite(
                    estadoNavegacion = estadoNavegacion,
                    modificarLimiteViewModel = modificarLimiteViewModel,
                )
                Spacer(modifier = Modifier.padding(5.dp))
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
fun tituloModificarLimiteScreen() {
    Box(modifier = Modifier
        .padding(start = 15.dp)) {
        Text(text = "Modificar Límete", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun tituloCuentas() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        Text(text = "Seleccionar Cuenta", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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

@Composable
fun tituloCategoria() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        Text(text = "Seleccionar Categoría", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun desplegableCategorias(
    estadoExpansionCategoria: MutableState<Boolean>,
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
            Button(onClick = { estadoExpansionCategoria.value = true }) {
                Text(text = seleccionarCategoria.value)
            }
            if (estadoExpansionCategoria.value) {
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
                                        estadoExpansionCategoria.value = false
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
fun cuadradoTextoCalculadoraIngreso(textoCalculadora: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(end = 20.dp, start = 20.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,

        ) {
        Text(
            text = textoCalculadora,
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Composable
fun tituloCalculadora() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        Text(text = "Introducir cantidad límite deseada", fontSize = 16.sp)
    }
}
@Composable
fun botonesCalculadoraIngreso(textoCalculadora: MutableState<String>, ingresosViewModel: IngresosViewModel = viewModel()) {

    val buttons = listOf(
        listOf("7", "8", "9"),
        listOf("4", "5", "6"),
        listOf("1", "2", "3"),
        listOf(",", "0", "C")
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                row.forEach { numeros ->
                    botonUnicoCalculadoraIngreso(numeros) {
                        when (numeros) {
                            "C" -> {
                                if (textoCalculadora.value.isNotEmpty()) {
                                    textoCalculadora.value = textoCalculadora.value.dropLast(1)
                                }
                            }
                            else -> {
                                textoCalculadora.value += numeros
                                val numeroDouble = textoCalculadora.value.toDoubleOrNull()
                                numeroDouble?.let {
                                    ingresosViewModel.actualizarNumeroIngresado(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun botonUnicoCalculadoraIngreso(numeros: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = numeros,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun botonNuevoLimite(
    estadoNavegacion: NavController,
    modificarLimiteViewModel: ModificarLimiteViewModel)
{
    Button(
        onClick = { estadoNavegacion.navigate("confirmacionNuevoLimite")},
        modifier = Modifier
            .width(135.dp)
            .height(65.dp),
    ){
        Box {
            Column (
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nuevo")
                Text(text = "Limite")
            }
        }
    }
}

//Referencia práctica 3 de Jose Enrique
@Composable
fun NavigacionIferior(modifier: Modifier = Modifier, estadoNavegacion: NavController) {
    // Implement composable here
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ){

        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Perfil")},
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text (text = "Perfil") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {  estadoNavegacion.navigate("Ingresos")},
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

@Composable
fun confirmacionNuevoLimite(estadoNavegacion: NavController){
    Box(
        modifier = Modifier
            .padding(end = 40.dp, start = 40.dp, top = 150.dp, bottom = 150.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Operación realizada con éxito"
            )
            Spacer(modifier = Modifier.padding(15.dp))

            Button(onClick = { estadoNavegacion.navigate("Perfil") },
                modifier = Modifier
                    .width(135.dp)
                    .height(65.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column (
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Volver al")
                        Text(text = "Perfil")
                    }
                }
            }
        }
    }
}

@Composable
fun emergenteErrorNuevoLimite(estadoNavegacion: NavController){
    Box(
        modifier = Modifier
            .padding(end = 40.dp, start = 40.dp, top = 150.dp, bottom = 150.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Error: Se ha producido un error en la actualización del límite"
            )
            Spacer(modifier = Modifier.padding(15.dp))

            Button(onClick = { estadoNavegacion.navigate("Perfil") },
                modifier = Modifier
                    .width(135.dp)
                    .height(65.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column (
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Volver al")
                        Text(text = "Perfil")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewModificarLimiteScreen(){
    val estadoNavegacion = rememberNavController()
    ModificarLimiteScreen(estadoNavegacion)
}