@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Login.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tfg.R


//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

//Función principal que contruye la pantalla de Login sobre un box central y un Column con las distintas funciones ordenadas
@Composable
fun LoginScreen(){
    Box (
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {
        Column (modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally){
            Logo()
            Spacer(modifier = Modifier.padding(30.dp))
            TituloEmail()
            Spacer(modifier = Modifier.padding(8.dp))
            CuadradoEmail()
            Spacer(modifier = Modifier.padding(15.dp))
            TituloPassword()
            Spacer(modifier = Modifier.padding(8.dp))
            CuadradoPassword()
            Spacer(modifier = Modifier.padding(15.dp))
            NuevoUsuario()
            Spacer(modifier = Modifier.padding(10.dp))
            BotonLogin()
        }
    }
}

//Función del Logotipo
@Composable
fun Logo(){
    Image(painter = painterResource(id = R.drawable.logoblanco), contentDescription = "Logo")
}

//Función del título del Email
@Composable
fun TituloEmail(){
    Text(text = "Correo Electrónico")
}

//Cuadro de texto que solicita el Email
@Composable
fun CuadradoEmail(){
    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("tuUsuario@gmail.com")},
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
@Composable
fun CuadradoPassword(){
    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        placeholder = {Text("********")},
        //keyboardActions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun NuevoUsuario() {
    Text(text = "¿Eres un nuevo usuario?", modifier = Modifier.clickable {  })
}

@Composable
fun BotonLogin() {
    Button(onClick = {},
        modifier = Modifier
        .fillMaxWidth()
        .height(45.dp),
        colors=ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            disabledContainerColor = Color.Green,
            contentColor = Color.White,
            disabledContentColor = Color.Black
        )) {
        Text(text = "Iniciar Sesión")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen(){
    LoginScreen()
}