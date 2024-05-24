package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Conexion
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import com.example.tfg.BBDD.Objetos.Categoria

/*
class TablaCategorias() {

    fun insertarCategoria(categoria: Categoria): Boolean {
        val query = "INSERT INTO categoria (id_cuenta, nombre_categoria, cantidad_limite) VALUES (?, ?, ?)"
        var statement: PreparedStatement? = null

        try {
            val conexion = Conexion()
            if (conexion == null) {
                conexion.establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            statement?.setInt(1, categoria.idCuenta)
            statement?.setString(2, categoria.nombreCategoria)
            statement?.setFloat(3, categoria.cantidadLimite)
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

    fun obtenerCategorias(): List<Categoria> {
        val query = "SELECT id_categoria, id_cuenta, nombre_categoria, cantidad_limite FROM categoria"
        val categorias = mutableListOf<Categoria>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                val categoria = Categoria(
                    idCategoria = resultSet.getInt("id_categoria"),
                    idCuenta = resultSet.getInt("id_cuenta"),
                    nombreCategoria = resultSet.getString("nombre_categoria"),
                    cantidadLimite = resultSet.getFloat("cantidad_limite")
                )
                categorias.add(categoria)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            cerrarConexion()
        }
        return categorias
    }

    fun eliminarCategoria(categoria: Categoria): Boolean {
        val query = "DELETE FROM categoria WHERE id_categoria = ? AND id_cuenta = ?"
        var statement: PreparedStatement? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            statement?.setInt(1, categoria.idCategoria)
            statement?.setInt(2, categoria.idCuenta)
            val filasAfectadas = statement?.executeUpdate()
            return filasAfectadas == 1
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            statement?.close()
            cerrarConexion()
        }
    }
}
 */