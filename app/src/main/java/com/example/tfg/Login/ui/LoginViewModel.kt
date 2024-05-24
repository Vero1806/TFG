package com.example.tfg.Login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Conexion
import com.example.tfg.BBDD.Objetos.Usuario
import com.example.tfg.BBDD.Tablas.TablaUsuario
import java.sql.Connection
import java.sql.DriverManager
import java.sql.DriverManager.getConnection
import java.sql.SQLException

class LoginViewModel : ViewModel() {

    // getter email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email
    // getter password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _loginHabilitado = MutableLiveData<Boolean>()
    val loginHabilitado: LiveData<Boolean> = _loginHabilitado

    init {
       _email.value = ""
       _password.value = ""
       _loginHabilitado.value = false
    }
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatepassword(newpassword: String) {
        _password.value = newpassword
    }

    //función de validación del botón
    fun onLoginCambios(email: String, password: String) {
        _email.value = email
        _password.value = password
       // _loginHabilitado.value = emailValido(email) && passwordValido(password)
    }

  // private fun passwordValido(password: String): Boolean = password.length > 8

  //  private fun emailValido(email: String): Boolean = email.length > 4

    fun CogerUsuario(tablaUsuario: TablaUsuario): Boolean {
        val user = Usuario(email.value,"",password.value)
        val usuarioBaseDatos = tablaUsuario.obtenerUsuarios(user)
        usuarioBaseDatos.correoUsuario
        usuarioBaseDatos.contrasenna
        validarLogin(user, usuarioBaseDatos)
        return true
    }

    // Function to validate login
    private fun validarLogin(user: Usuario, usuarioBaseDatos: Usuario) {
       if (user.correoUsuario == usuarioBaseDatos.correoUsuario && user.contrasenna == usuarioBaseDatos.contrasenna){

           _loginHabilitado.value = true
       }
    }

    fun onLoginSeleccion(): Boolean {
        val conexion = Conexion()
        if (CogerUsuario(conexion) == true) {
            return true
        }
    }
}

