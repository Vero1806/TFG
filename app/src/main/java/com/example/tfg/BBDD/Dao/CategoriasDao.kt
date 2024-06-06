package com.example.tfg.BBDD.Dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tfg.BBDD.tablas.Categoria_tabla

@Dao
interface CategoriaDao {
    //@Query("SELECT * FROM Categoria_tabla WHERE id_cuenta = :idCuenta")
    //fun obtenerTodasLasCategoriasDeUnaCuenta(): List<Categoria_tabla>

    @Insert
    fun insertarCategoria(categoria_tabla: Categoria_tabla)
}