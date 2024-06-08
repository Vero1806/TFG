package com.example.tfg.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tfg.Interfaz.Categorias.CategoriasScreen
import com.example.tfg.Interfaz.CompartirCuenta.CompartirCuentaScreen
import com.example.tfg.Interfaz.Cuentas.CuentasScreen
import com.example.tfg.Interfaz.Cuentas.ElegirLimiteNuevaCuenta
import com.example.tfg.Interfaz.Cuentas.confirmarCrearCuenta
import com.example.tfg.Interfaz.Cuentas.emergenteError
import com.example.tfg.Interfaz.Gastos.GastosScreen
import com.example.tfg.Interfaz.Gastos.boxConfirmacionCuentaGasto
import com.example.tfg.Interfaz.Gastos.boxaceptacionGasto
import com.example.tfg.Interfaz.Ingresos.IngresosScreen
import com.example.tfg.Interfaz.Ingresos.boxConfirmacionCuentaIngreso
import com.example.tfg.Interfaz.Ingresos.boxaceptacionIngreso
import com.example.tfg.Interfaz.Login.LoginScreen
import com.example.tfg.Interfaz.Perfil.PerfilScreen
import com.example.tfg.Interfaz.Registro.RegistroScreen

//Referencia https://www.youtube.com/watch?v=jMRTUGXnZ2s
@Composable
fun NavegacionApp(estadoNavegacion: NavHostController){
    NavHost(navController = estadoNavegacion, startDestination = "Cuentas"){
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
        composable("ConfirmacionCuentaIngreso"){
            boxConfirmacionCuentaIngreso(estadoNavegacion)
        }
        composable("aceptacionIngreso"){
            boxaceptacionIngreso(estadoNavegacion)
        }
        composable("Cuentas"){
            CuentasScreen(estadoNavegacion)
        }
        composable("ElegirLimiteNuevaCuenta"){
            ElegirLimiteNuevaCuenta(estadoNavegacion)
        }
        composable("cajaError"){
            emergenteError(estadoNavegacion)
        }
        composable("confirmarCrearCuenta"){
            confirmarCrearCuenta(estadoNavegacion)
        }
        composable("CompartirCuenta"){
            CompartirCuentaScreen(estadoNavegacion)
        }
        composable("Gastos"){
            GastosScreen(estadoNavegacion)
        }
        composable("ConfirmacionCuentaGasto"){
            boxConfirmacionCuentaGasto(estadoNavegacion)
        }
        composable("aceptacionGasto"){
            boxaceptacionGasto(estadoNavegacion)
        }
        composable("Categorias"){
            CategoriasScreen(estadoNavegacion)
        }
    }
}