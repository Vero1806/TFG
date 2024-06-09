package com.example.tfg.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tfg.BBDD.Dao.CategoriaDao
import com.example.tfg.BBDD.Dao.CategoriasGenericasDao
import com.example.tfg.BBDD.Dao.CuentaDao
import com.example.tfg.BBDD.Dao.TransaccionDao
import com.example.tfg.BBDD.Dao.UsuarioCuentaDao
import com.example.tfg.BBDD.Dao.UsuarioDao
import com.example.tfg.BBDD.Tablas.Categorias_Genericas_tabla
import com.example.tfg.BBDD.tablas.Categoria_tabla
import com.example.tfg.BBDD.tablas.Cuenta_tabla
import com.example.tfg.BBDD.tablas.Transaccion_tabla
import com.example.tfg.BBDD.tablas.Usuario_Cuenta_tabla
import com.example.tfg.BBDD.tablas.Usuario_tabla


@Database(entities = arrayOf(Usuario_tabla::class, Cuenta_tabla::class, Usuario_Cuenta_tabla::class, Transaccion_tabla::class, Categoria_tabla::class, Categorias_Genericas_tabla::class), version = 1)
abstract class BaseDatos: RoomDatabase() {
    abstract fun UsuariosDAO(): UsuarioDao
    abstract fun cuentaDao(): CuentaDao
    abstract fun usuarioCuentaDao(): UsuarioCuentaDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun transaccionDao(): TransaccionDao
    abstract fun categoriasGenericasDao(): CategoriasGenericasDao

}