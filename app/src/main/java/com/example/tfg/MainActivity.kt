package com.example.tfg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tfg.Navegacion.NavegacionApp
import com.example.tfg.ui.theme.TFGTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TFGTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val estadoNavegacion = rememberNavController()
                    NavegacionApp(estadoNavegacion = estadoNavegacion)
                }
            }
        }
    }
    @Preview
    @Composable
    fun PreviewMain(){
        val estadoNavegacion = rememberNavController()
        NavegacionApp(estadoNavegacion = estadoNavegacion)
    }
}



