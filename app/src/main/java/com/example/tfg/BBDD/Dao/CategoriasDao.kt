package com.example.tfg.BBDD.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tfg.BBDD.tablas.Categoria_tabla

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM Categoria_tabla WHERE id_cuenta = :id_cuenta")
    fun obtenerTodasLasCategoriasDeUnaCuenta(id_cuenta: Int): List<Categoria_tabla>

    @Insert
    suspend fun insertarCategoria(categoria_tabla: Categoria_tabla)
}