package com.example.tfg.Interfaz.Registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfg.BBDD.Objetos.Usuario
import com.example.tfg.BBDD.Tablas.TablaUsuario

class RegistroViewModel : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> get() = _nombre

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _registroHabilitado = MutableLiveData<Boolean>()
    val registroHabilitado: LiveData<Boolean> = _registroHabilitado

    init {
        _nombre.value = ""
        _email.value = ""
        _password.value = ""
        _registroHabilitado.value = false
    }

    private fun validarColorRegistro() {
        _registroHabilitado.value = emailValido(_email.value?: "") && contrasennaValida(_password.value?: "")
    }
    fun onRegistroCambios(nombre: String, email: String, password: String) {
        _nombre.value = nombre
        _email.value = email
        _password.value = password
        validarColorRegistro()
    }

    //Comprueba que el correo lleve @ y .com
    private fun emailValido(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun contrasennaValida(password: String): Boolean = password.length > 4

    fun CrearUsuario(tablaUsuario: TablaUsuario): Boolean {
        val user = Usuario(_email.value ?: "", _nombre.value ?: "", _password.value ?: "")
        val usuarioBaseDatos = tablaUsuario.obtenerUsuarios(user)
        return if (usuarioBaseDatos == null) {
            RegistroValido(user, usuarioBaseDatos)
        } else {
            false
        }
    }
    private fun RegistroValido (user: Usuario, usuarioBaseDatos: Usuario): Boolean {
        return user.correoUsuario != usuarioBaseDatos.correoUsuario
    }

    //Booleano que devuelto true o false si se crea el usuario puede ser util para control
    /*fun onCrearRegistro(): Boolean {
        val tablaUsuario = TablaUsuario()
        return CrearUsuario(tablaUsuario)
    }*/
}