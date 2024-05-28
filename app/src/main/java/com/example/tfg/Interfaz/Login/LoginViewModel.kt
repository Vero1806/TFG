package com.example.tfg.Interfaz.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Conexion
import com.example.tfg.BBDD.Objetos.Usuario
import com.example.tfg.BBDD.Tablas.TablaUsuario

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

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
        validarLogin()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        validarLogin()
    }

    fun onLoginCambios(email: String, password: String) {
        _email.value = email
        _password.value = password
        validarLogin()
    }

    private fun validarLogin() {
        _loginHabilitado.value = !(_email.value.isNullOrEmpty() || _password.value.isNullOrEmpty())
    }

    fun CogerUsuario(tablaUsuario: TablaUsuario): Boolean {
        val user = Usuario(_email.value ?: "", "", _password.value ?: "")
        val usuarioBaseDatos = tablaUsuario.obtenerUsuarios(user)
        return validarLogin(user, usuarioBaseDatos)
    }

    private fun validarLogin(user: Usuario, usuarioBaseDatos: Usuario): Boolean {
        return user.correoUsuario == usuarioBaseDatos.correoUsuario && user.contrasenna == usuarioBaseDatos.contrasenna
    }

    fun onLoginSeleccion(): Boolean {
        val conexion = Conexion()
        val tablaUsuario = TablaUsuario(conexion)
        return CogerUsuario(tablaUsuario)
    }
}
