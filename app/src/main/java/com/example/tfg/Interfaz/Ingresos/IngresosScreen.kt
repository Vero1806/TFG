@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Ingresos
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun IngresosScreen(estadoNavegacion: NavController, ingresosViewModel: IngresosViewModel = viewModel()) {
    val textoCalculadora = rememberSaveable { mutableStateOf("") }

    val estadoExpansionCuentas = rememberSaveable { mutableStateOf(false) }
    val seleccionarCuenta = rememberSaveable {
        mutableStateOf(ingresosViewModel.cuentas.getOrNull(1)?.nombreCuenta ?: "")}
    val nombresCuentas = rememberSaveable { mutableStateOf(ingresosViewModel.listaNombresCuentas) }

    val estadoExpansionCategorias = rememberSaveable { mutableStateOf(false) }

    var seleccionarCategoria = rememberSaveable { mutableStateOf(ingresosViewModel.categorias.getOrNull(1) ?.nombreCategoria ?: "") }

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
                    tituloIngersoScreen()
                    Spacer(modifier = Modifier.width(16.dp))
                    Logo()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    tituloCuenta()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCuentas(estadoExpansionCuentas = estadoExpansionCuentas,
                        nombresCuentas = nombresCuentas,
                        seleccionarCuenta = seleccionarCuenta)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    tituloCategoria()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCategorias(estadoExpansionCategorias  = estadoExpansionCategorias,
                        listaCategorias = ingresosViewModel.categorias.map { it.nombreCategoria},
                        seleccionarCategoria = seleccionarCategoria)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally){
                    cuadradoTextoCalculadora(textoCalculadora.value)
                    Spacer(modifier = Modifier.padding(5.dp))
                    botonesCalculadora(textoCalculadora)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Column (modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End) {
                    BotonIngresar()
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
fun tituloIngersoScreen() {
    Box(modifier = Modifier
        .padding(start = 15.dp)) {
        Text(text = "Nuevo Ingreso", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun tituloCuenta() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        Text(text = "Seleccionar Cuenta", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun desplegableCuentas(estadoExpansionCuentas: MutableState<Boolean>, nombresCuentas: MutableState<List<String>>, seleccionarCuenta: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Button(onClick = { estadoExpansionCuentas.value = true }) {
                Text(text = seleccionarCuenta.value)
            }
            if (estadoExpansionCuentas.value) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize()
                ) {
                    Column {
                        nombresCuentas.value.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        seleccionarCuenta.value = item
                                        estadoExpansionCuentas.value = false
                                    }
                                    .padding(8.dp)
                            ) {
                                Text(text = item, fontSize = 16.sp, color = MaterialTheme.colorScheme.surface)
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
fun desplegableCategorias(estadoExpansionCategorias: MutableState<Boolean>, listaCategorias: List<String>, seleccionarCategoria: MutableState<String>) {
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
                        listaCategorias.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        seleccionarCategoria.value = item
                                        estadoExpansionCategorias.value = false
                                    }
                                    .padding(8.dp)
                            ) {
                                Text(text = item, fontSize = 16.sp, color = MaterialTheme.colorScheme.surface)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun cuadradoTextoCalculadora(textoCalculadora: String) {


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
fun botonesCalculadora(textoCalculadora: MutableState<String>) {


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
                    //.padding(horizontal = 10.dp)
                    .padding(vertical = 10.dp),

                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                row.forEach { number ->
                    botonUnicoCalculadora(number) {
                        when (number) {
                            "C" -> {
                                if (textoCalculadora.value.isNotEmpty()) {
                                    textoCalculadora.value = textoCalculadora.value.dropLast(1)
                                }
                            }
                            else -> textoCalculadora.value += number
                        }
                    }
                }
            }
            //Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun botonUnicoCalculadora(number: String, onClick: () -> Unit) {
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
            text = number,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BotonIngresar() {
    Button(onClick = {},
        modifier = Modifier
            .width(135.dp)
            .height(65.dp)
            .padding(end = 15.dp),
        colors=ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onError,
            contentColor = Color.White,
            disabledContentColor = Color.Black
        )) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 5.dp, start = 7.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Realizar Ingreso"
            )
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
            selected = true,
            onClick = {  },
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
fun PreviewIngresos(){
    val estadoNavegacion = rememberNavController()
    IngresosScreen(estadoNavegacion)
}