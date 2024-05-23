package com.example.tfg.Login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Conexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.DriverManager.getConnection
import java.sql.SQLException

class LoginViewModel : ViewModel() {
    //LoginViewModel(var conexion: Conexion) : ViewModel()


    // getter email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email
//    init {
//        _email.value = ""
//    }
//    fun updateEmail(newEmail: String) {
//        _email.value = newEmail
//    }


    // getter password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    //    init {
//        _password.value = ""
//    }
//    fun updatepassword(newpassword: String) {
//        _password.value = newpassword
//    }
    private val _loginHabilitado = MutableLiveData<Boolean>()
    val loginHabilitado: LiveData<Boolean> = _loginHabilitado


    //función de cambios en el login
    fun onLoginCambios(email: String, password: String) {
        _email.value = email
        _password.value = password
        //_loginHabilitado.value = emailValido(email) && passwordValido(password)
    }

    //está formula no va aquí, si no en el registro para validar el imail. Aquí debemos buscar la conexión con la base de datos.
    private fun passwordValido(password: String): Boolean = password.length > 8

    fun onLoginSeleccion(){

    }
}

