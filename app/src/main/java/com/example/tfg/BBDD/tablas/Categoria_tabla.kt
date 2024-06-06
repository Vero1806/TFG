package com.example.tfg.BBDD.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria_tabla")
data class Categoria_tabla(
    @PrimaryKey(autoGenerate = true) val id_categoria: Int,
    val id_cuenta: Int,
    val nombre_categoria: String,
    val cantidad_limite: Double
)
