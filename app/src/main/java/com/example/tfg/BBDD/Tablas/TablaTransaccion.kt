import com.example.tfg.BBDD.Conexion
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import com.example.tfg.BBDD.Objetos.Transaccion
import java.time.LocalDateTime

class TablaTransaccion(override var conexion: Connection?) : Conexion() {

    init {
        establecerConexion()
    }

    fun insertarTransaccion(transaccion: Transaccion): Boolean {
        val query = "INSERT INTO transaccion (id_cuenta, id_categoria, cantidad_transaccion, concepto_transaccion, fecha_transaccion) VALUES (?, ?, ?, ?, ?)"
        var statement: PreparedStatement? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            statement?.setInt(1, transaccion.idCuenta)
            statement?.setInt(2, transaccion.idCategoria)
            statement?.setFloat(3, transaccion.cantidadTransaccion)
            statement?.setString(4, transaccion.conceptoTransaccion)
            //statement?.setObject(5, transaccion.fechaTransaccion)
            statement?.executeUpdate()
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            statement?.close()
            cerrarConexion()
        }
    }

    fun obtenerTransacciones(): List<Transaccion> {
        val query = "SELECT id_transaccion, id_cuenta, id_categoria, cantidad_transaccion, concepto_transaccion, fecha_transaccion FROM transaccion"
        val transacciones = mutableListOf<Transaccion>()
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                val transaccion = Transaccion(
                    idTransaccion = resultSet.getInt("id_transaccion"),
                    idCuenta = resultSet.getInt("id_cuenta"),
                    idCategoria = resultSet.getInt("id_categoria"),
                    cantidadTransaccion = resultSet.getFloat("cantidad_transaccion"),
                    conceptoTransaccion = resultSet.getString("concepto_transaccion")
                    //fechaTransaccion = resultSet.getObject("fecha_transaccion",LocalDateTime)
                )
                transacciones.add(transaccion)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            statement?.close()
            cerrarConexion()
        }

        return transacciones
    }

    //elimina por el idTranssaccion y el idCuenta. Veremos si hay que hacer cambios luego
    fun eliminarTransaccion(transaccion: Transaccion): Boolean {
        val query = "DELETE FROM transaccion WHERE id_transaccion = ? and idCuenta = ?"
        var statement: PreparedStatement? = null

        try {
            if (conexion == null) {
                establecerConexion()
            }
            statement = conexion?.prepareStatement(query)
            statement?.setInt(1, transaccion.idTransaccion)
            statement?.setInt(2, transaccion.idCuenta)
            val filasAfectadas = statement?.executeUpdate()
            return filasAfectadas == 1
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            statement?.close()
            cerrarConexion()
        }
    }
}
