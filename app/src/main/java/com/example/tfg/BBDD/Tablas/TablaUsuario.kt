package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Objetos.Categoria
import com.example.tfg.BBDD.Objetos.Cuenta
import com.example.tfg.BBDD.Objetos.Usuario
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

//Se usa override para poder heredad de la clase abstracta conexion
class TablaUsuario() {

    //init llama siempre a establecerConexion aunque no se inicialice en la clase que llama a TablaUsuario
    // lo que simplifica el código futuro

    fun insertarUsuario(usuario: Usuario): Boolean {
        val query =
            "INSERT INTO usuario (correo_usuario, nombre_usuario, contrasenna) VALUES (?, ?, ?)"
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

    fun obtenerUsuarios(usuario: Usuario): Usuario {

        return Usuario("vero@g.com", "Vero", "1234")
    }

    fun obtenerCuentas(): List<Cuenta> {

        return listOf(
            Cuenta(1, "Personal", 5000.0),
            Cuenta(2, "Ahorros", 10000.0),
            Cuenta(3, "Compartida", 15000.0)
        )
    }
    fun obtenerNombresCuentas(): List<String> {
        val cuentas = obtenerCuentas()
        return cuentas.map { it.nombreCuenta }
    }

    fun obtenerCategorias(): List<Categoria> {

        return listOf(
            Categoria(1, 1, "Hogar", 1000.0f),
            Categoria(2, 1, "Alimetación", 10000.0f),
            Categoria(3, 1, "Transporte", 15000.0f),
            Categoria(4, 1, "Ocio", 5000.0f),
            Categoria(5, 2, "Hogar", 10000.0f),
            Categoria(6, 2, "Transporte", 15000.0f),
        )
    }
    fun obtenerNombresCategorias(): List<String> {
        val categorias = obtenerCategorias()
        return categorias.map { it.nombreCategoria }
    }
}


