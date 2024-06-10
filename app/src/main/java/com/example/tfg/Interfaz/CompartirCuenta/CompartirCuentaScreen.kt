package com.example.tfg.Interfaz.CompartirCuenta

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
import androidx.compose.runtime.livedata.observeAsState
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompartirCuentaScreen(
    estadoNavegacion: NavController,
    compartirCuentaViewModel: CompartirCuentaViewModel = viewModel()
) {

    val emailCompartirCuenta = compartirCuentaViewModel.emailCompartirCuenta.observeAsState(initial = "")

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver atrás",
                        modifier = Modifier.clickable  {estadoNavegacion.navigate("Cuentas") }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    tituloCompartirCuenta()
                    Spacer(modifier = Modifier.width(10.dp))
                    Logo()
                }
                Spacer(modifier = Modifier.padding(30.dp))
                explicacion1CompartirCuenta()
                Spacer(modifier = Modifier.padding(10.dp))
                cuentaACompartir()
                Spacer(modifier = Modifier.padding(15.dp))
                explicacion2CompartirCuenta()
                Spacer(modifier = Modifier.padding(10.dp))
                CuadradoNombreCompatirCuenta(emailCompartirCuenta.value){
                    compartirCuentaViewModel.actualizarNombreNuevaCuenta(it)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                botonConfirmarCompatirCuenta(estadoNavegacion = estadoNavegacion,
                    emailCompartirCuenta = emailCompartirCuenta.value,
                    compartirCuentaViewModel = compartirCuentaViewModel)
                Spacer(modifier = Modifier.padding(15.dp))
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
                contentDescription = "Logo"
            )
        }else {
            Image(
                painter = painterResource(id = R.drawable.logo_light_mode),
                contentDescription = "Logo"
            )
        }
    }
}
@Composable
fun tituloCompartirCuenta() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
    ) {
        Text(text = "Compartir Cuentas", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun explicacion1CompartirCuenta(){
    Box {
        Column (
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Selecciona la cuenta", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier .padding(3.dp))
            Text(text = "que quieras compartir", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
            Text(text = "Introduce el correo electrónico de la persona con la que deseas compartir la cuenta", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
fun botonConfirmarCompatirCuenta(
    estadoNavegacion: NavController,
    compartirCuentaViewModel: CompartirCuentaViewModel = viewModel(),
    emailCompartirCuenta: String,){
    Button(onClick = {
        if (compartirCuentaViewModel.emailCompatirValido(emailCompartirCuenta)) {
        estadoNavegacion.navigate("confirmarCompatrirCuenta")
    } else {
        estadoNavegacion.navigate("emergenteErrorNocorreovalido")
    }},
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCompartirCuentas(){
    val estadoNavegacion = rememberNavController()
    CompartirCuentaScreen(estadoNavegacion)
}


@Composable
fun confirmarCompatrirCuenta (estadoNavegacion: NavController){
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
                modifier = Modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Cuenta compartida con éxito"
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
fun emergenteErrorNocorreovalido(estadoNavegacion: NavController){

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
                    .padding(start = 20.dp, end = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Error: El correo no está registrado en la aplicación"
            )
            Spacer(modifier = Modifier.padding(15.dp))

            Button(onClick = { estadoNavegacion.navigate("CompartirCuenta") },
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
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Volver")
                        Text(text = "atrás")
                    }
                }
            }
        }
    }
}

