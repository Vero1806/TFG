package com.example.tfg.BBDD.Dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.example.tfg.BBDD.tablas.Usuario_tabla

@Dao
interface UsuarioDao {

    @Query("Select * FROM usuario_tabla where correo_usuario = :correoUsuario")
    suspend fun selectUsuariosPorCorreo (correoUsuario: String): Usuario_tabla

    @Insert
    fun insertarUsuario(usuario_tabla: Usuario_tabla)
}

