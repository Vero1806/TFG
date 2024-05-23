package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Conexion
import com.example.tfg.BBDD.Objetos.UsuarioCuenta
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class TablaUsuarioCuenta (override var conexion: Connection?) : Conexion(){
    init {
        establecerConexion()
    }
    fun obtenerUsuariosCuenta(): List<UsuarioCuenta> {
        val query = "SELECT correo_usuario, nombre_usuario, contrasenna FROM usuarios"
        val usuariosCuentas = mutableListOf<UsuarioCuenta>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                val usuarioCuenta = UsuarioCuenta(
                    correoUsuario = resultSet.getString("correo_usuario"),
                    idCuenta = resultSet.getInt("id_cuenta"),

                )
                usuariosCuentas.add(usuarioCuenta)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            cerrarConexion()
        }
        return usuariosCuentas
    }
}