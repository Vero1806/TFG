@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Registro
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

//Función principal que contruye la pantalla de Registro sobre un box central y un Column con las distintas funciones ordenadas
@Composable
fun RegistroScreen(){
    Box (
        Modifier
            .fillMaxSize()
            .padding(30.dp)) {
        Column (modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally){
            com.example.tfg.Interfaz.Login.Logo()
            Spacer(modifier = Modifier.padding(30.dp))
            TituloResgistro()
            Spacer(modifier = Modifier.padding(8.dp))
            TituloNombre()
            Spacer(modifier = Modifier.padding(8.dp))
            CuadradoNombre()
            Spacer(modifier = Modifier.padding(8.dp))
            com.example.tfg.Interfaz.Login.TituloEmail()
            Spacer(modifier = Modifier.padding(8.dp))
            CuadradoEmail()
            Spacer(modifier = Modifier.padding(8.dp))
            com.example.tfg.Interfaz.Login.TituloPassword()
            Spacer(modifier = Modifier.padding(8.dp))
            CuadradoPassword()
            Spacer(modifier = Modifier.padding(15.dp))
            Row (modifier = Modifier){
                BotonCancelar()
                Spacer(modifier = Modifier.padding(10.dp))
                BotonRegistrarse()
            }

        }
    }
}

//Función del Logo
@Composable
fun Logo(){
    Image(painter = painterResource(id = R.drawable.logoblanco), contentDescription = "Logo")
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
fun BotonCancelar() {
    Button(onClick = {},
        modifier = Modifier
            .width(150.dp)
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
            .width(150.dp)
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen(){
    RegistroScreen()
}