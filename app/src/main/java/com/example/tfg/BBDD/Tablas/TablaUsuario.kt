package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Objetos.Usuario
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

//Se usa override para poder heredad de la clase abstracta conexion
class TablaUsuario() {

    //init llama siempre a establecerConexion aunque no se inicialice en la clase que llama a TablaUsuario
    // lo que simplifica el código futuro

    fun insertarUsuario(usuario: Usuario): Boolean {
        val query = "INSERT INTO usuario (correo_usuario, nombre_usuario, contrasenna) VALUES (?, ?, ?)"
        var statement: PreparedStatement? = null
        var conex: Connection? = null

        try {
            //conex = conexion.establecerConexion()
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
            //conexion.cerrarConexion(conex)
        }
    }

    fun obtenerUsuarios(usuario: Usuario): Usuario{

        return Usuario("vero@g.com","Vero","1234")
    }
}


