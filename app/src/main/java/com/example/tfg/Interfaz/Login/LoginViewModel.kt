package com.example.tfg.Interfaz.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Usuario

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

    fun onLoginCambios(email: String, password: String) {
        _email.value = email
        _password.value = password
        validarColorLogin()
    }

    private fun validarColorLogin() {
        _loginHabilitado.value = !(_email.value.isNullOrEmpty() && _password.value.isNullOrEmpty())
    }

//    fun CogerUsuario(tablaUsuario: usua): Boolean {
//        val user = Usuario(_email.value ?: "", "", _password.value ?: "")
//        val usuarioBaseDatos = tablaUsuario.obtenerUsuarios(user)
//        return if (usuarioBaseDatos != null) {
//            validarLogin(user, usuarioBaseDatos)
//        } else {
//            false
//        }
//    }

    private fun validarLogin(user: Usuario, usuarioBaseDatos: Usuario): Boolean {
        return user.correoUsuario == usuarioBaseDatos.correoUsuario && user.contrasenna == usuarioBaseDatos.contrasenna
    }

//    fun comprobarUsiario() {
//        val comprobartUsuario = Usuario_tabla(
//            correo_usuario = _email.value ?: "",
//            contrasenna = _password.value ?: ""
//        )
//        usuarioDao.selectUsuariosPorCorreo()
//    }

//    fun onLoginSeleccion(): Boolean {
//        val tablaUsuario = TablaUsuario()
//        return CogerUsuario(tablaUsuario)
//    }
}
