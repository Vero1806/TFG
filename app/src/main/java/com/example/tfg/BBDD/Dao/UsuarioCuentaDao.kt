package com.example.tfg.BBDD.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tfg.BBDD.tablas.Usuario_Cuenta_tabla

@Dao
interface UsuarioCuentaDao {

    @Query("SELECT * FROM usuario_cuenta WHERE correo_usuario = :correo_usuario")
    fun obtenerCuentasPorUsuario(correo_usuario: String): List<Usuario_Cuenta_tabla>

    @Query("SELECT * FROM usuario_cuenta WHERE id_cuenta = :id_cuenta")
    fun obtenerUsuariosPorCuenta(id_cuenta: Int): List<Usuario_Cuenta_tabla>

    @Insert
    suspend fun insertarRelacionCuentaUsuario(usuarioCuenta: Usuario_Cuenta_tabla)
}
