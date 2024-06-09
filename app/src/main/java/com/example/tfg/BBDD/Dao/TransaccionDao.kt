package com.example.tfg.BBDD.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tfg.BBDD.tablas.Transaccion_tabla

@Dao
interface TransaccionDao {

    @Query("SELECT * FROM transaccion")
    fun obtenerTodasLasTransacciones(): List<Transaccion_tabla>

    @Query("SELECT * FROM transaccion WHERE id_transaccion = :id_transaccion")
    fun obtenerTransaccionPorId(id_transaccion: Int): Transaccion_tabla?

    @Query("SELECT * FROM transaccion WHERE id_cuenta = :id_cuenta")
    fun obtenerTransaccionesPorCuenta(id_cuenta: Int): List<Transaccion_tabla>

    @Insert
    suspend fun insertarTransaccion(transaccion: Transaccion_tabla)

}