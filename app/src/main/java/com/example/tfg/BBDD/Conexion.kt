package com.example.tfg.BBDD

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Conexion() {

    val url =
        "jdbc:mysql://biyd3n4dtmb0jjzgvbbt-mysql.services.clever-cloud.com:3306/biyd3n4dtmb0jjzgvbbt"
    val user = "usfz3caowueu5xnz"
    val password = "DdzXiEi6ZnNlGvrYgl5r"

    //Cuando se usa ? detrás de una sentencia de conexión se está haciendo una especie de TERNARIO lo que permite controlar
    //las excepciones de respuestas nulas por parte de la base de datos. En este Package de base de datos lo veremos mucho.

    //abre la conexión con la base de datos y devuelve null si falla
    fun establecerConexion() {
        CoroutineScope(Dispatchers.IO).launch {
            var connection: Connection? = null
            try {
                connection = DriverManager.getConnection(url, user, password)
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                try {
                    connection?.close()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }
        }
    }

    //cierra conexión pero no devuelve nada si lo consigue.
    fun cerrarConexion(conexion: Connection?) {
        try {
            conexion?.close()
            println("Conexión cerrada exitosamente")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}