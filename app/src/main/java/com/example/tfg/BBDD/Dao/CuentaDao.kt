package com.example.tfg.BBDD.Dao

import androidx.room.Insert
import androidx.room.Query
import com.example.tfg.BBDD.tablas.Cuenta_tabla

interface CuentaDao {
    @Query("SELECT * FROM cuenta_tabla WHERE id_cuenta IN (SELECT id_cuenta FROM usuario_cuenta WHERE correo_usuario = :correoUsuario)")
    fun obtenerCuentasPorUsuario(correoUsuario: String): List<Cuenta_tabla>

    @Insert
    suspend fun insertarCuenta(cuentaTabla: Cuenta_tabla)
}
