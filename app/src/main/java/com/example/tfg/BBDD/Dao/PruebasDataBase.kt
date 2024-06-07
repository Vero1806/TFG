package com.example.tfg.BBDD.Dao

import com.example.tfg.BBDD.tablas.Usuario_tabla

//Se usa override para poder heredad de la clase abstracta conexion


class PruebasDataBase() {

    suspend fun obtenerUsuarios(usuario: Usuario_tabla): Usuario_tabla{

        return Usuario_tabla( "vero@gmail.com","Vero","1234")
    }
}


