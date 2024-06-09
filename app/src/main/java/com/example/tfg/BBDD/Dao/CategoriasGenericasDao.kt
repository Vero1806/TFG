package com.example.tfg.BBDD.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tfg.BBDD.Tablas.Categorias_Genericas_tabla


@Dao
interface CategoriasGenericasDao {
    @Query("SELECT * FROM categorias_genericas_tabla")
    fun obtenerTodasLasCategoriasGenericas(): List<Categorias_Genericas_tabla>

    @Query("SELECT * FROM categorias_genericas_tabla WHERE id_categoria = :id_categoria")
    fun obtenerCategoriaGenericaPorId(id_categoria: Int): Categorias_Genericas_tabla?

    @Insert
    suspend fun insertarCategoriaGenerica(categoria: Categorias_Genericas_tabla)

}