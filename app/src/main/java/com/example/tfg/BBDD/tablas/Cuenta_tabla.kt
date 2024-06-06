package com.example.tfg.BBDD.tablas

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cuenta_tabla",
        foreignKeys = [
    ForeignKey(
        entity = Cuenta_tabla::class,
        parentColumns = ["id_cuenta"],
        childColumns = ["id_cuenta"],
        onDelete = ForeignKey.CASCADE // Opcional, dependiendo de tus necesidades
    )
])
data class Cuenta_tabla(
    @PrimaryKey(autoGenerate = true) val id_cuenta: Int = 0,
    val nombre_cuenta: String = "",
    val limite_total: Double = 0.0
)