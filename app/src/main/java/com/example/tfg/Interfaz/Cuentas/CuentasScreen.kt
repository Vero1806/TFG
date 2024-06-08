@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Cuentas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.BBDD.Objetos.Cuenta
import com.example.tfg.R

//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuentasScreen(estadoNavegacion: NavController, cuentasViewModel: CuentasViewModel = viewModel()) {

    val cuentas = cuentasViewModel.cuentas.observeAsState(emptyList())
    val nombreNuevaCuenta by cuentasViewModel.nombreNuevaCuenta.observeAsState(initial = "")
    val limiteNuevaCuenta by cuentasViewModel.limiteNuevaCuenta.observeAsState(initial = " ")

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
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column (modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End){
                    Logo()
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Row{
                    TituloTusCuentas()
                    Spacer(modifier = Modifier.width(30.dp))
                    TituloLimites()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    ColumnaNombresCuentas(cuentas = cuentas.value)
                    Spacer(modifier = Modifier.width(30.dp))
                    ColumnaLimitesCuentas(cuentas = cuentas.value)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                TituloNuevaCuenta()
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    TituloNombreNuevaCuenta()
                    Spacer(modifier = Modifier.width(30.dp))
                    CuadradoNombreNuevaCuenta(nombreNuevaCuenta) {
                        cuentasViewModel.actualizarNombreNuevaCuenta(it)
                    }
                }
                Spacer(modifier = Modifier.padding(25.dp))
                Row {
                    BotonCompartirCuenta(estadoNavegacion = estadoNavegacion)
                    Spacer(modifier = Modifier.width(30.dp))
                    BotonCrearNuevaCuenta(estadoNavegacion = estadoNavegacion)
                }
                Spacer(modifier = Modifier.padding(5.dp))
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
fun TituloTusCuentas(){
    Text(text = "Tus cuentas", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}


@Composable
fun TituloLimites(){
    Text(text = "Límites", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun ColumnaNombresCuentas(cuentas: List<Cuenta>){
    Column(modifier = Modifier.padding(16.dp)) {
        cuentas.forEach { cuenta ->
            Text(text = "${cuenta.nombreCuenta}", fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun ColumnaLimitesCuentas(cuentas: List<Cuenta>){
    Column(modifier = Modifier.padding(16.dp)) {
        cuentas.forEach { cuenta ->
            Text(text = " ${cuenta.limiteTotal}", fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}

//Función del título del Email
@Composable
fun TituloNuevaCuenta(){
    Text(text = "Crear nueva cuenta:", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun TituloNombreNuevaCuenta(){
    Box(modifier = Modifier
        .padding(start = 15.dp, top = 5.dp)) {
        Text(text = "Nombre:", fontSize = 16.sp)
    }
}

@Composable
fun CuadradoNombreNuevaCuenta(nombreNuevaCuenta: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = nombreNuevaCuenta,
        onValueChange = onTextFieldChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 20.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        singleLine = true,
        maxLines = 1
    )
}
@Composable
fun BotonCompartirCuenta(estadoNavegacion: NavController) {
    Button(onClick = {estadoNavegacion.navigate("")},
        modifier = Modifier
            .width(135.dp)
            .height(65.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ))
    {
        Box {
            Column (
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Compartir")
                Text(text = "Cuenta")
            }
        }
    }
}

@Composable
fun BotonCrearNuevaCuenta(estadoNavegacion: NavController, cuentasViewModel: CuentasViewModel = viewModel()) {
    Button(
        onClick = {
            if (cuentasViewModel.comprobarNumeroDeCuentas()) {
                estadoNavegacion.navigate("ElegirLimiteNuevaCuenta")
            } else {
                estadoNavegacion.navigate("cajaError")
            }
            },
        modifier = Modifier
            .width(135.dp)
            .height(65.dp),
       ){
        Box {
            Column (
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Crear nueva")
                Text(text = "Cuenta")
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
            selected = true,
            onClick = {  },
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
fun ElegirLimiteNuevaCuenta(estadoNavegacion: NavHostController, cuentasViewModel: CuentasViewModel = viewModel()) {

    Box(){
        //meter aquí la calculadora de la ventana de gastos y arreglar cosas

    }
}
@Composable
fun TituloLimiteNuevaCuenta(estadoNavegacion: NavController){
    Box(modifier = Modifier
        .padding(start = 15.dp, top = 5.dp)) {
        Text(text = "Limite:", fontSize = 16.sp)
    }
}

@Composable
fun CuadradoLimiteNuevaCuenta(limiteNuevaCuenta: Double, onTextFieldChanged: (String) -> Unit) {
    val limiteEnLetras = limiteNuevaCuenta.toString()
    TextField(
        value = limiteEnLetras,
        onValueChange = onTextFieldChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 33.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun emergenteError(estadoNavegacion: NavController){

    Box(){
        //Cuadrado te texto emergente de que tienes demasiadas cuentas creas!!
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCuentas(){
    val estadoNavegacion = rememberNavController()
    CuentasScreen(estadoNavegacion)
}