package com.example.tfg.BBDD.Tablas

import com.example.tfg.BBDD.Objetos.CategoriaGenerica
import com.example.tfg.BBDD.Conexion
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

open class TablaCategoriaGenerica(override var conexion: Connection?) : Conexion() {
    init {
        establecerConexion()
    }

    open fun obtenerCategoriasGenerica(): List<CategoriaGenerica> {
        val query = "SELECT id_categoria, nombre_categoria FROM categorias_genericas"
        val categoriasGenericas = mutableListOf<CategoriaGenerica>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                val categoriaGenerica = CategoriaGenerica(
                    idCategoria = resultSet.getInt("id_categoria"),
                    nombreCategoria = resultSet.getString("nombre_categoria")
                )
                categoriasGenericas.add(categoriaGenerica)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            cerrarConexion()
        }

        return categoriasGenericas
    }

}