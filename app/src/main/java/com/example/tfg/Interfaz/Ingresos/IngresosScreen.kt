@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.tfg.Interfaz.Ingresos
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun IngresosScreen(estadoNavegacion: NavController, ingresosViewModel: IngresosViewModel = viewModel()) {

    Scaffold(
        bottomBar = { NavigacionIferior(estadoNavegacion = estadoNavegacion) }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column (modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End){
                    Logo()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    tituloCuenta()
                    Spacer(modifier = Modifier.padding(5.dp))
                    desplegableCuentas()
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row {
                    tituloCategoria()
                    Spacer(modifier = Modifier.padding(8.dp))
                    desplegableCategorias()
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally){
                    CalculatorDisplay()
                    Spacer(modifier = Modifier.padding(5.dp))
                    CalculatorButtons()
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Column (modifier = Modifier.align(Alignment.End),
                    horizontalAlignment = Alignment.End) {
                    BotonIngresar()
                }
            }
        }
    }
}

//Función del Logo
@Composable
fun Logo() {
    Box(modifier = Modifier.size(80.dp,80.dp)) {
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
fun tituloCuenta(){
    Text(text = "Seleccionar Cuenta",  fontSize = 16.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun desplegableCuentas() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val items = listOf("Item 1", "Item 2", "Item 3")
    var selectedItem by rememberSaveable { mutableStateOf(items[0]) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Button(onClick = { expanded = true }) {
                Text(text = selectedItem)
            }
            if (expanded) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                        .wrapContentSize()
                ) {
                    Column {
                        items.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedItem = item
                                        expanded = false
                                    }
                                    .padding(8.dp)
                            ) {
                                Text(text = item, fontSize = 16.sp, color = MaterialTheme.colorScheme.surface)
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun tituloCategoria(){
    Text(text = "Seleccionar Categoría",  fontSize = 16.sp, fontWeight = FontWeight.Bold)
}
@Composable
fun desplegableCategorias() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val items = listOf("Item 1", "Item 2", "Item 3")
    var selectedItem by rememberSaveable { mutableStateOf(items[0]) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Button(onClick = { expanded = true }) {
                Text(text = selectedItem)
            }
            if (expanded) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                        .wrapContentSize()
                ) {
                    Column {
                        items.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedItem = item
                                        expanded = false
                                    }
                                    .padding(8.dp)
                            ) {
                                Text(text = item, fontSize = 16.sp, color = MaterialTheme.colorScheme.surface)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CalculatorDisplay() {
    val displayText = rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = displayText.value,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun CalculatorButtons() {
    val displayText = rememberSaveable { mutableStateOf("") }

    val buttons = listOf(
        listOf("7", "8", "9"),
        listOf("4", "5", "6"),
        listOf("1", "2", "3"),
        listOf(",", "0", "C")
    )

    buttons.forEach { row ->
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            row.forEach { number ->
                CalculatorButton(number) {
                    displayText.value += number
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(number: String, onClick: () -> Unit) {
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
fun BotonIngresar() {
    Button(onClick = {},
        modifier = Modifier
            .width(120.dp)
            .height(65.dp),
        colors=ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onError,
            contentColor = Color.Black,
        )) {
        Text(
            modifier =
            Modifier.align(Alignment.CenterVertically),
            text = "Realizar Ingreso"
        )
    }
}
//Referencia práctica 3 de Jose Enrique
@Composable
fun NavigacionIferior(modifier: Modifier = Modifier, estadoNavegacion: NavController) {
    // Implement composable here
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ){

        NavigationBarItem(
            selected = false,
            onClick = { estadoNavegacion.navigate("Perfil")},
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text (text = "Perfil") }
        )
        NavigationBarItem(
            selected = true,
            onClick = {  },
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
            onClick = { estadoNavegacion.navigate("Categorias") },
            icon = { Icon(painter = painterResource(id = R.drawable.category), contentDescription = null) },
            label = { Text (text = "Categorias") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewIngresos(){
    val estadoNavegacion = rememberNavController()
    IngresosScreen(estadoNavegacion)
}