package com.example.tfg.Interfaz.Registro
import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.tfg.BBDD.VolleySingleton
import org.json.JSONException
import org.json.JSONObject

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
        _registroHabilitado.value = emailValido(_email.value ?: "") && contrasennaValida(_password.value ?: "")
    }

    fun onRegistroCambios(nombre: String, email: String, password: String) {
        _nombre.value = nombre
        _email.value = email
        _password.value = password
        validarColorRegistro()
    }

    fun registrarUsuario(context: Context) {
        val url = "https://localhost:7028/api/Usuarios/"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(context, volleyError.message, Toast.LENGTH_LONG).show()
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros = HashMap<String, String>()
                parametros["email"] = email.value ?: ""
                parametros["nombre"] = nombre.value ?: ""
                parametros["contrasenna"] = password.value ?: ""
                return parametros
            }
        }

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest)
    }
    private fun emailValido(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun contrasennaValida(password: String): Boolean = password.length > 4
}
