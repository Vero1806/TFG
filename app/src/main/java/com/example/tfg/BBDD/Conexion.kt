package com.example.tfg.BBDD

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

abstract class Conexion {

    //Cuando se usa ? detrás de una sentencia de conexión se está haciendo una especie de TERNARIO lo que permite controlar
    //las excepciones de respuestas nulas por parte de la base de datos. En este Package de base de datos lo veremos mucho.
    abstract var conexion: Connection?

    //abre la conexión con la base de datos y devuelve null si falla
    fun establecerConexion() {
        val url =
            "jdbc:mysql://biyd3n4dtmb0jjzgvbbt-mysql.services.clever-cloud.com:3306/biyd3n4dtmb0jjzgvbbt"
        val user = "usfz3caowueu5xnz"
        val Password = "DdzXiEi6ZnNlGvrYgl5r"
        return try {
            conexion = DriverManager.getConnection(url, user, Password)
        } catch (e: SQLException) {
            e.printStackTrace()
            conexion = null
        }
    }

    //cierra conexión pero no devuelve nada si lo consigue.
    fun cerrarConexion() {
        try {
            conexion?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}