package com.example.tfg.BBDD.tablas

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "usuario_cuenta",
    primaryKeys = ["correo_usuario", "id_cuenta"],
    foreignKeys = [
        ForeignKey(
            entity = Usuario_tabla::class,
            parentColumns = ["correo_usuario"],
            childColumns = ["correo_usuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Cuenta_tabla::class,
            parentColumns = ["id_cuenta"],
            childColumns = ["id_cuenta"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Usuario_Cuenta_tabla(
    val correo_usuario: String,
    val id_cuenta: Int
)