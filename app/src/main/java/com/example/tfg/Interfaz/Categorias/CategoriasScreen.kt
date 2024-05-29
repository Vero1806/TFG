@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Categorias
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R

//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriasScreen(estadoNavegacion: NavController, categoriasViewModel: CategoriasViewModel = viewModel()) {
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
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column (modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End){
                    Logo()
                }
                Spacer(modifier = Modifier.padding(30.dp))
                TituloResgistro()
                Spacer(modifier = Modifier.padding(8.dp))
                TituloNombre()
                Spacer(modifier = Modifier.padding(8.dp))
                CuadradoNombre()
                Spacer(modifier = Modifier.padding(8.dp))
                TituloEmail()
                Spacer(modifier = Modifier.padding(8.dp))
                CuadradoEmail()
                Spacer(modifier = Modifier.padding(8.dp))
                TituloPassword()
                Spacer(modifier = Modifier.padding(8.dp))
                CuadradoPassword()
                Spacer(modifier = Modifier.padding(15.dp))
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

//Función del Logo
@Composable
fun Logo() {
    Box(modifier = Modifier.size(80.dp,80.dp)) {
        Image(
            painter = painterResource(id = R.drawable.dollarmoneylogo),
            contentDescription = "Logo"
        )
    }
}

@Composable
fun TituloResgistro(){
    Text(text = "Crear nueva cuenta ")
}


@Composable
fun TituloNombre(){
    Text(text = "Nombre:")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuadradoNombre(){
    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        //placeholder = {Text("")},
        //keyboardActions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1
    )
}

//Función del título del Email
@Composable
fun TituloEmail(){
    Text(text = "Correo Electrónico:")
}

//Cuadro de texto que solicita el Email
@Composable
fun CuadradoEmail(){
    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("")},
        //keyboardActions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}
//Función del título de la contraseña
@Composable
fun TituloPassword(){
    Text(text = "Contraseña")
}

//Cuadro de texto que solicita la contraseña
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuadradoPassword(){
    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("")},
        //keyboardActions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun BotonCancelar(estadoNavegacion: NavController) {
    Button(onClick = {estadoNavegacion.navigate("Login")},
        modifier = Modifier
            .width(80.dp)
            .height(45.dp),
        colors=ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            disabledContainerColor = Color.Green,
            contentColor = Color.White,
            disabledContentColor = Color.Black
        )) {
        Text(text = "Cancelar")
    }
}

@Composable
fun BotonRegistrarse() {
    Button(onClick = {},
        modifier = Modifier
            .width(80.dp)
            .height(45.dp),
        colors=ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            disabledContainerColor = Color.Green,
            contentColor = Color.White,
            disabledContentColor = Color.Black
        )) {
        Text(text = "Registrarse")
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
            label = { Text (text = "Perfil")}
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Ingresos") },
            icon = { Icon(painter = painterResource(id = R.drawable.more), contentDescription = null) },
            label = { Text (text = "Ingreso")}
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Cuentas") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_credit_card_24), contentDescription = null) },
            label = { Text (text = "Cuentas")}
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Gastos") },
            icon = { Icon(painter = painterResource(id = R.drawable.less), contentDescription = null) },
            label = { Text (text = "Gastos")}
        )
        NavigationBarItem(
            selected = true,
            onClick = {  },
            icon = { Icon(painter = painterResource(id = R.drawable.category), contentDescription = null) },
            label = { Text (text = "Categorias")}
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPerfil(){
    val estadoNavegacion = rememberNavController()
    CategoriasScreen(estadoNavegacion)
}