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


    // getter email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email
    // getter password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

   init {
       _email.value = ""
       _password.value = ""
    }
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatepassword(newpassword: String) {
        _password.value = newpassword
    }
    private val _loginHabilitado = MutableLiveData<Boolean>()
    val loginHabilitado: LiveData<Boolean> = _loginHabilitado


    //función de cambios en el login
    fun onLoginCambios(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginHabilitado.value = emailValido(email) && passwordValido(password)
    }

   private fun passwordValido(password: String): Boolean = password.length > 8

    private fun emailValido(email: String): Boolean = email.length > 8

    fun onLoginSeleccion(){

    }
}

