package com.example.tfg.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.Interfaz.Login.LoginScreen
import com.example.tfg.Interfaz.Registro.RegistroScreen

@Composable
fun NavegacionApp(){
    val estadoNavegacion = rememberNavController()
    NavHost(navController = estadoNavegacion, startDestination = ControladorPantalla.Login.ruta){
        composable(route = ControladorPantalla.Login.ruta){
            LoginScreen(estadoNavegacion)
        }
        composable(route = ControladorPantalla.Registro.ruta){
            RegistroScreen(estadoNavegacion)
        }
    }
}