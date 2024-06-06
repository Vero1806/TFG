package com.example.tfg.BBDD.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias_genericas_tabla")
data class Categorias_Genericas_tabla(
    @PrimaryKey val id_categoria: Int,
    val nombre_categoria: String
)
