package com.example.tfg.BBDD

import java.sql.Connection

class ConexionConcreta (): Conexion() {
    override var conexion: Connection?
        get() = conexion
        set(value) {}

}