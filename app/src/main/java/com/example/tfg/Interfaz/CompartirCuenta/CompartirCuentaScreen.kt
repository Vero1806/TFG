package com.example.tfg.Interfaz.CompartirCuenta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tfg.Interfaz.Cuentas.Logo
import com.example.tfg.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompartirCuentaScreen(
    estadoNavegacion: NavController,
    compartirCuentaViewModel: CompartirCuentaViewModel = viewModel()
) {

    val emailCompartirCuenta by compartirCuentaViewModel.emailCompartirCuenta.observeAsState(initial = "")

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver atrás",
                        modifier = Modifier.clickable { estadoNavegacion.popBackStack() }
                    )
                },
                title = { Text(" ") }
            )
        }
    ){innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End
                ) {
                    Logo()
                }
                Spacer(modifier = Modifier.padding(15.dp))
                tituloCompartirCuenta()
                Spacer(modifier = Modifier.padding(15.dp))
                explicacion1CompartirCuenta()
                Spacer(modifier = Modifier.padding(10.dp))
                cuentaACompartir()
                Spacer(modifier = Modifier.padding(15.dp))
                explicacion2CompartirCuenta()
                Spacer(modifier = Modifier.padding(10.dp))
                CuadradoNombreCompatirCuenta(emailCompartirCuenta){
                    compartirCuentaViewModel.actualizarNombreNuevaCuenta(it)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                botonConfirmarCompatirCuenta(estadoNavegacion = estadoNavegacion)
                Spacer(modifier = Modifier.padding(5.dp))
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
fun tituloCompartirCuenta(){
    Text(text = "Compartir Cuentas", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun explicacion1CompartirCuenta(){
    Box {
        Column (
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Selecciona la cuenta", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "que quieras compartir", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun cuentaACompartir() {

    var estadoExpansionCuenta = rememberSaveable { mutableStateOf(false) }
    val nombresCuenta = listOf("Personal", "Ahorros", "Compartida")
    var seleccionarCuenta = rememberSaveable { mutableStateOf(nombresCuenta[0]) }

    Button(onClick = { estadoExpansionCuenta.value = true }) {
        Text(text = seleccionarCuenta.value)
    }
    if (estadoExpansionCuenta.value) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(start = 5.dp, end = 5.dp)
        ) {
            Column (
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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

@Composable
fun explicacion2CompartirCuenta(){
    Box {
        Column (
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Introduce el correo electrónico de la persona con la que deseas compartir la cuenta", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuadradoNombreCompatirCuenta(emailCompartirCuenta: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = emailCompartirCuenta,
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
fun botonConfirmarCompatirCuenta(estadoNavegacion: NavController){
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
                Text(text = "Compartir")
                Text(text = "Cuenta")
            }
        }
    }
}

@Composable
fun NavigacionIferior(modifier: Modifier = Modifier, estadoNavegacion: NavController) {
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