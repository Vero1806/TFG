@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R




//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw
//Referencia topBar = https://youtube.com/watch?v=eNuaMn4ukdo

//Función principal que contruye la pantalla de Registro sobre un box central y un Column con las distintas funciones ordenadas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    estadoNavegacion: NavController,
    registroViewModel: RegistroViewModel = viewModel()
) {
    val nombre by registroViewModel.nombre.observeAsState(initial = "")
    val email by registroViewModel.email.observeAsState(initial = "")
    val password by registroViewModel.password.observeAsState(initial = "")
    val registroHabilitado: Boolean by registroViewModel.registroHabilitado.observeAsState(initial = false)
    val context = LocalContext.current

    Scaffold(
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
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()
                Spacer(modifier = Modifier.padding(20.dp))
                TituloResgistro()
                Spacer(modifier = Modifier.padding(8.dp))
                TituloNombre()
                Spacer(modifier = Modifier.padding(8.dp))
                CuadradoNombre(nombre) { registroViewModel.onRegistroCambios(it, email, password) }
                Spacer(modifier = Modifier.padding(8.dp))
                TituloEmail()
                Spacer(modifier = Modifier.padding(8.dp))
                CuadradoEmail(email) { registroViewModel.onRegistroCambios(nombre, it, password) }
                Spacer(modifier = Modifier.padding(8.dp))
                TituloPassword()
                Spacer(modifier = Modifier.padding(8.dp))
                CuadradoPassword(password) { registroViewModel.onRegistroCambios(nombre, email, it) }
                Spacer(modifier = Modifier.padding(15.dp))
                BotonRegistrarse(registroHabilitado) {
                    registroViewModel.registrarUsuario()
                    estadoNavegacion.navigate("Perfil")
                }
            }
        }
    }
}

//Función del Logotipo cambia el logo del diseño blanco al diseño negro dependiendo del modo del móvil
@Composable
fun Logo(){
    if(isSystemInDarkTheme()){
        Image(
            painter = painterResource(id = R.drawable.logo_dark_mode),
            contentDescription = "Logo",
            Modifier.size(150.dp)
        )
    }else {
        Image(
            painter = painterResource(id = R.drawable.logo_light_mode),
            contentDescription = "Logo",
            Modifier.size(250.dp)
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

@Composable
fun CuadradoNombre(nombre: String, onTextFieldChanged: (String) -> Unit){
    TextField(value = nombre, onValueChange = {onTextFieldChanged(it)}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text(" ")},
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
fun CuadradoEmail(email: String, onTextFieldChanged: (String) -> Unit){
    TextField(value = email, onValueChange = {onTextFieldChanged(it)}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("nuevoUsuario@gmail.com")},
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
fun CuadradoPassword(password: String, onTextFieldChanged: (String) -> Unit){

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(value = password, onValueChange = {onTextFieldChanged(it)}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("")},
        singleLine = true,
        maxLines = 1,

        //Conviente el texto en caracter oculto y comprueba si el ojo esta abierto o no para enseñar la contraseña al usuario
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        //Oculta las opciones de palabras sugeridas
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            //variable que tiene el icono de ojo abierto y cerrado dependiendo del if
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, null)
            }
        }
    )
}

@Composable
fun BotonRegistrarse(registroHabilitado: Boolean, onClick: () -> Unit) {
    Button( onClick = onClick,
        modifier = Modifier
            .width(150.dp)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.primary
        ),
        enabled = registroHabilitado
    ) {
        Text(text = "Registrarse")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegistronScreen(){
    val estadoNavegacion = rememberNavController()
    RegistroScreen(estadoNavegacion)
}