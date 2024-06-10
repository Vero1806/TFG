@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.AgregarCategoria
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R

//Referencia: https://www.youtube.com/watch?v=EmUx8wgRxJw

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarCategoriaScreen(estadoNavegacion: NavController, agregarCategoriasViewModel: AgregarCategoriasViewModel = viewModel()) {

    val nombreNuevaCategoria by agregarCategoriasViewModel.nombreNuevaCategoria.observeAsState("")

    val cuentas by agregarCategoriasViewModel.cuentas.collectAsState()

    var estadoExpansionCategoria = rememberSaveable { mutableStateOf(false) }
    var seleccionarCuenta = rememberSaveable { mutableStateOf(cuentas.firstOrNull()?.nombreCuenta ?: "") }

    val textoCalculadora = rememberSaveable { mutableStateOf("") }

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) }
    ){ innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TituloCategorias()
                    Spacer(modifier = Modifier.width(16.dp))
                    Logo()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TituloElegirCuentas()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCuentas(estadoExpansionCategorias = estadoExpansionCategoria,
                        nombresCategoria = cuentas.map { it.nombreCuenta },
                        seleccionarCategoria = seleccionarCuenta)
                }
                Spacer(modifier = Modifier.padding(15.dp))
                TituloNombreNuevaCategoria()
                Spacer(modifier = Modifier.padding(10.dp))
                CuadradoNombreNuevaCategoria(nombreNuevaCategoria) {
                    agregarCategoriasViewModel.actualizarNombreNuevaCuenta(it)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                TituloNombreNuevoLimiteCategoria()
                Spacer(modifier = Modifier.padding(10.dp))
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally){
                    cuadradoTextoCalculadora(textoCalculadora.value)
                    Spacer(modifier = Modifier.padding(5.dp))
                    botonesCalculadora(textoCalculadora)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                botonAgregarCategoria(estadoNavegacion = estadoNavegacion,
                    agregarCategoriasViewModel = agregarCategoriasViewModel,
                    nombreNuevaCategoria = nombreNuevaCategoria,
                    idCuenta = cuentas.find { it.nombreCuenta == seleccionarCuenta.value }?.idCuenta ?: -1
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}

@Composable
fun Logo() {
    Box(modifier = Modifier
        .size(80.dp, 80.dp)
        .padding(8.dp)) {
        if(isSystemInDarkTheme()){
            Image(
                painter = painterResource(id = R.drawable.logo_dark_mode),
                contentDescription = "Logo",
                Modifier.size(250.dp)
            )
        }else {
            Image(
                painter = painterResource(id = R.drawable.logo_light_mode),
                contentDescription = "Logo",
                Modifier.size(250.dp)
            )
        }
    }
}
@Composable
fun TituloCategorias() {
    Text(text = "Nueva Categorias", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun TituloElegirCuentas(){
    Text(text = "Elegir Cuenta:", fontSize = 25.sp)
}

@Composable
fun desplegableCuentas(
    estadoExpansionCategorias: MutableState<Boolean>,
    nombresCategoria: List<String>,
    seleccionarCategoria: MutableState<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column {
            Button(onClick = { estadoExpansionCategorias.value = true }) {
                Text(text = seleccionarCategoria.value)
            }
            if (estadoExpansionCategorias.value) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize()
                ) {
                    Column {
                        nombresCategoria.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        seleccionarCategoria.value = item
                                        estadoExpansionCategorias.value = false
                                    }
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = item,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.surface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TituloNombreNuevaCategoria(){
    Box(modifier = Modifier
        .padding(start = 15.dp, top = 25.dp)) {
        Text(text = "Nombre nueva categoría:", fontSize = 18.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuadradoNombreNuevaCategoria(nombreNuevaCategoria: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = nombreNuevaCategoria,
        onValueChange = onTextFieldChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 20.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        singleLine = true,
        maxLines = 1
    )
}
@Composable
fun TituloNombreNuevoLimiteCategoria(){
    Box(modifier = Modifier
        .padding(start = 15.dp, top = 25.dp)) {
        Text(text = "Limite de la nueva categoría:", fontSize = 18.sp)
    }
}
@Composable
fun cuadradoTextoCalculadora(textoCalculadora: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(end = 20.dp, start = 20.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,

        ) {
        Text(
            text = textoCalculadora,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun botonesCalculadora(textoCalculadora: MutableState<String>) {
    val buttons = listOf(
        listOf("7", "8", "9"),
        listOf("4", "5", "6"),
        listOf("1", "2", "3"),
        listOf(",", "0", "C")
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(horizontal = 10.dp)
                    .padding(vertical = 10.dp),

                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                row.forEach { number ->
                    botonUnicoCalculadora(number) {
                        when (number) {
                            "C" -> {
                                if (textoCalculadora.value.isNotEmpty()) {
                                    textoCalculadora.value = textoCalculadora.value.dropLast(1)
                                }
                            }
                            else -> textoCalculadora.value += number
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun botonUnicoCalculadora(number: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun botonAgregarCategoria(
    estadoNavegacion: NavController,
    agregarCategoriasViewModel: AgregarCategoriasViewModel,
    nombreNuevaCategoria: String,
    idCuenta: Int) {
    Button(
        onClick = {
            if (agregarCategoriasViewModel.comprobarNombreCategoriaPorCuenta(
                    nombreNuevaCategoria,
                    idCuenta
                )
            ) {
                estadoNavegacion.navigate("confirmacionNuevaCategoria")
            } else {
                estadoNavegacion.navigate("emergenteErrorAgregarCategoria")
            }
        },
        modifier = Modifier
            .width(135.dp)
            .height(65.dp),
    ){
        Box {
            Column (
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Agregar")
                Text(text = "Categoría")
            }
        }
    }
}
@Composable
private fun NavigacionIferior(modifier: Modifier = Modifier, estadoNavegacion: NavController) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ){

        NavigationBarItem(
            selected = false,
            onClick = {estadoNavegacion.navigate("Perfil")},
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text (text = "Perfil") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Ingresos") },
            icon = { Icon(painter = painterResource(id = R.drawable.more), contentDescription = null) },
            label = { Text (text = "Ingreso") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Cuentas") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_credit_card_24), contentDescription = null) },
            label = { Text (text = "Cuentas") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Gastos") },
            icon = { Icon(painter = painterResource(id = R.drawable.less), contentDescription = null) },
            label = { Text (text = "Gastos") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { "Categorias" },
            icon = { Icon(painter = painterResource(id = R.drawable.category), contentDescription = null) },
            label = { Text (text = "Categorias") }
        )
    }
}
@Composable
fun confirmacionNuevaCategoria(estadoNavegacion: NavController){

    Box(
        modifier = Modifier
            .padding(end = 40.dp, start = 40.dp, top = 150.dp, bottom = 150.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Categoría creada correctamente"
            )
            Spacer(modifier = Modifier.padding(15.dp))

            Button(onClick = { estadoNavegacion.navigate("Perfil") },
                modifier = Modifier
                    .width(135.dp)
                    .height(65.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Volver al")
                        Text(text = "Perfil")
                    }
                }
            }
        }
    }
}

@Composable
fun emergenteErrorAgregarCategoria(estadoNavegacion: NavController){

    Box(
        modifier = Modifier
            .padding(end = 40.dp, start = 40.dp, top = 150.dp, bottom = 150.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Error: el nombre de la categoría que quiere crear ya existe en esta cuenta"
            )
            Spacer(modifier = Modifier.padding(15.dp))

            Button(onClick = { estadoNavegacion.navigate("Categorias") },
                modifier = Modifier
                    .width(135.dp)
                    .height(65.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp)
                        .wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Volver al")
                        Text(text = "Atrás")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAgregarCategorias(){
    val estadoNavegacion = rememberNavController()
    AgregarCategoriaScreen(estadoNavegacion)
}