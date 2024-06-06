package com.example.tfg.BBDD.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaccion")
data class Transaccion_tabla (

    @PrimaryKey(autoGenerate = true) val id_transaccion: Int = 0,
    val id_cuenta: Int,
    val id_categoria: Int,
    val cantidad_transaccion: Double,
    val concepto_transaccion: String?,
    val fecha_transaccion: Long?
    )