package com.example.tfg.BBDD.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_tabla")
data class Usuario_tabla(
    @PrimaryKey val correo_usuario: String,
    val nombre_usuario: String,
    val contrasenna: String
)