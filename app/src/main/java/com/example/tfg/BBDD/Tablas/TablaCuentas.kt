package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Conexion
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import com.example.tfg.BBDD.Objetos.Cuenta

class TablaCuenta (override var conexion: Connection?) : Conexion() {

    init {
        establecerConexion()
    }

    //Cuando se pone :Boolean es porque se le está pidiendo a la función que devuelva true o false si ha podido o no hacer el insert
    //Puede ser interesante usar este control en la interfaz para que el usuario sepa si se han podido insertar sus datos o no correctamente
    fun insertarCuenta(cuenta: Cuenta): Boolean {
        val query = "INSERT INTO cuenta (nombre_cuenta, limite_total) VALUES (?, ?)"
        var statement: PreparedStatement? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            statement?.setString(1, cuenta.nombreCuenta)
            statement?.setFloat(2, cuenta.limiteTotal)
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

    fun obtenerCuentas(): List<Cuenta> {
        val query = "SELECT id_cuenta, nombre_cuenta, limite_total FROM cuenta"
        val cuentas = mutableListOf<Cuenta>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                val cuenta = Cuenta(
                    idCuenta = resultSet.getInt("id_cuenta"),
                    nombreCuenta = resultSet.getString("nombre_cuenta"),
                    limiteTotal = resultSet.getFloat("limite_total")
                )
                cuentas.add(cuenta)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            cerrarConexion()
        }

        return cuentas
    }
}
