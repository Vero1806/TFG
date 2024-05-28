package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Conexion
import com.example.tfg.BBDD.Objetos.Usuario
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

//Se usa override para poder heredad de la clase abstracta conexion
class TablaUsuario(private val conexion: Conexion) {

    //init llama siempre a establecerConexion aunque no se inicialice en la clase que llama a TablaUsuario
    // lo que simplifica el código futuro

    fun insertarUsuario(usuario: Usuario): Boolean {
        val query = "INSERT INTO usuario (correo_usuario, nombre_usuario, contrasenna) VALUES (?, ?, ?)"
        var statement: PreparedStatement? = null
        var conex: Connection? = null

        try {
            conex = conexion.establecerConexion()
            statement = conex?.prepareStatement(query)
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
            conexion.cerrarConexion(conex)
        }
    }

    fun obtenerUsuarios(usuario: Usuario): Usuario {
        val query = "SELECT correo_usuario, nombre_usuario, contrasenna FROM usuario WHERE correo_usuario = ?"
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        var conex: Connection? = null

        try {
            conex = conexion.establecerConexion()
            statement = conex?.prepareStatement(query)
            statement?.setString(1, usuario.correoUsuario)
            resultSet = statement?.executeQuery()

            if (resultSet?.next() == true) {
                return Usuario(
                    correoUsuario = resultSet.getString("correo_usuario"),
                    nombreUsuario = resultSet.getString("nombre_usuario"),
                    contrasenna = resultSet.getString("contrasenna")
                )
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            conexion.cerrarConexion(conex)
        }
        return Usuario("", "", "")
    }
}


