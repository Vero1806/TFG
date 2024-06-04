package com.example.tfg.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tfg.Interfaz.Categorias.CategoriasScreen
import com.example.tfg.Interfaz.Cuentas.CuentasScreen
import com.example.tfg.Interfaz.Gastos.GastosScreen
import com.example.tfg.Interfaz.Ingresos.IngresosScreen
import com.example.tfg.Interfaz.Login.LoginScreen
import com.example.tfg.Interfaz.Perfil.PerfilScreen
import com.example.tfg.Interfaz.Registro.RegistroScreen

//Referencia https://www.youtube.com/watch?v=jMRTUGXnZ2s
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
        composable("Ingresos"){
            IngresosScreen(estadoNavegacion)
        }
        composable("Cuentas"){
            CuentasScreen(estadoNavegacion)
        }
        composable("Gastos"){
            GastosScreen(estadoNavegacion)
        }
        composable("Categorias"){
            CategoriasScreen(estadoNavegacion)
        }
    }
}