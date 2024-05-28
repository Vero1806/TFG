package com.example.tfg.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.Interfaz.Login.LoginScreen
import com.example.tfg.Interfaz.Perfil.PerfilScreen
import com.example.tfg.Interfaz.Registro.RegistroScreen

@Composable
fun NavegacionApp(estadoNavegacion: NavHostController){
    NavHost(navController = estadoNavegacion, startDestination = "Login"){
        composable("login"){
            LoginScreen(estadoNavegacion)
        }
        composable("Registro"){
            RegistroScreen(estadoNavegacion)
        }
        composable("Perfil"){
            PerfilScreen(estadoNavegacion)
        }
    }
}