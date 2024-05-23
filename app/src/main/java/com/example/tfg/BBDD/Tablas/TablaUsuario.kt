package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Conexion
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import com.example.tfg.BBDD.Objetos.Usuario

//Se usa override para poder heredad de la clase abstracta conexion
class TablaUsuario (override var conexion: Connection?) : Conexion() {

    //init llama siempre a establecerConexion aunque no se inicialice en la clase que llama a TablaUsuario
    // lo que simplifica el código futuro
    init {
        establecerConexion()
    }

    fun insertarUsuario(usuario: Usuario): Boolean {
        val query = "INSERT INTO usuarios (correo_usuario, nombre_usuario, contrasenna) VALUES (?, ?, ?)"
        var statement: PreparedStatement? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            statement?.setString(1, usuario.correoUsuario)
            statement?.setString(2, usuario.nombreUsuario)
            statement?.setString(3, usuario.contrasenna)
            statement?.executeUpdate()
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            statement?.close()
            cerrarConexion()
        }
    }

    fun obtenerUsuarios(): List<Usuario> {
        val query = "SELECT correo_usuario, nombre_usuario, contrasenna FROM usuarios"
        val usuarios = mutableListOf<Usuario>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                val usuario = Usuario(
                    correoUsuario = resultSet.getString("correo_usuario"),
                    nombreUsuario = resultSet.getString("nombre_usuario"),
                    contrasenna = resultSet.getString("contrasenna")
                )
                usuarios.add(usuario)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            cerrarConexion()
        }
        return usuarios
    }
}
