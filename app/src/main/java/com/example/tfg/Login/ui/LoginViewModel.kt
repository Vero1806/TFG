package com.example.tfg.Login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

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
        _loginHabilitado.value = emailValido(email) && passwordValido(password)
    }

    //función de validación del email
    private fun emailValido(email: String): Boolean = email.length > 6
    private fun passwordValido(password: String): Boolean = password.length > 6

    fun onLoginSeleccion(){

    }
}

