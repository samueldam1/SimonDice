package com.example.simondice


import androidx.compose.ui.Modifier
import com.example.simondice.ui.theme.SimonDiceTheme
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Interfaz gráfica de usuario
 */
@Composable
fun GUI(vm: VM) { // llamada a la clasa VM
    // remember: funcion exclusiva de @Composable que redibuja la UI
    var labelRonda by remember { mutableStateOf(Datos.ronda) }

    val fuenteSize = if (Datos.ronda <= 10) 16.sp else 14.sp // tiene que estar en sp
    // definimos un scope para la IU
    val iuScope = rememberCoroutineScope()


    Column {
        // Indicador de ronda
        Row {

            Spacer(modifier = Modifier.width(125.dp)) // Agrega espacio horizontal

            Column (
                horizontalAlignment = Alignment.End
            ){
                Text("RONDA")
                Text(
                    text = labelRonda.toString(),
                    fontSize = fuenteSize
                )
            }
        }

        // Botones de colores
        Column (
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        ){
            // Primera fila
            Row (
                modifier = Modifier
                    .padding(5.dp)
            ){
                Button( // Boton azul
                    colors =  ButtonDefaults.buttonColors(Colores.CLASE_AZUL.color.value),
                    onClick = {
                        vm.resaltarBoton(Colores.CLASE_AZUL)
                        vm.aumentarSecuenciaUsuario(Colores.CLASE_AZUL)
                    },
                    modifier = Modifier
                        .background(Colores.CLASE_AZUL.color.value)
                        .padding(10.dp)
                ) {}

                Spacer(modifier = Modifier.width(10.dp)) // Agrega espacio horizontal

                Button( // Boton verde
                    colors =  ButtonDefaults.buttonColors(Colores.CLASE_VERDE.color.value),
                    onClick = {
                        vm.resaltarBoton(Colores.CLASE_VERDE)
                        vm.aumentarSecuenciaUsuario(Colores.CLASE_VERDE)
                    },
                    modifier = Modifier
                        .background(Colores.CLASE_VERDE.color.value)
                        .padding(10.dp)
                ) {}
            }
            // Segunda fila
            Row (
                modifier = Modifier
                    .padding(5.dp)
            ){
                Button( // Boton rojo
                    colors =  ButtonDefaults.buttonColors(Colores.CLASE_ROJO.color.value),
                    onClick = {
                        vm.resaltarBoton(Colores.CLASE_ROJO)
                        vm.aumentarSecuenciaUsuario(Colores.CLASE_ROJO)
                    },
                    modifier = Modifier
                        .background(Colores.CLASE_ROJO.color.value)
                        .padding(10.dp)
                ) {}

                Spacer(modifier = Modifier.width(10.dp)) // Agrega espacio horizontal

                Button( // Boton amarillo
                    colors =  ButtonDefaults.buttonColors(Colores.CLASE_AMARILLO.color.value),
                    onClick = {
                        vm.resaltarBoton(Colores.CLASE_AMARILLO)
                        vm.aumentarSecuenciaUsuario(Colores.CLASE_AMARILLO)
                    },
                    modifier = Modifier
                        .background(Colores.CLASE_AMARILLO.color.value)
                        .padding(10.dp)
                    /*modifier = Modifier
                        .background(Color.Black)
                        .padding(10.dp)*/
                ) {}
            }
        }

        // Botones 'Start' y 'Send'
        Row (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            TextButton( // Boton "Start"
                onClick = { vm.inicializarJuego()}
            ) {
                Text("Start")
            }

            Spacer(modifier = Modifier.width(10.dp)) // Agrega espacio horizontal

            Button( // Boton "Send"
                onClick = { vm.comprobarSecuencia() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "Icono send",
                    modifier = Modifier.size(20.dp,20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceTheme {
        val vm = VM()
        GUI(vm)
    }
}

