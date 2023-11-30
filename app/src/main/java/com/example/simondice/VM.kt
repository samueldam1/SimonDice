package com.example.simondice

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

/**
 * ViewModel del juego
 * */
class VM : ViewModel() {

    /**
     * Inicializa el juego
     */
    fun inicializarJuego() {
        Log.d("Dev", "Iniciando juego...")
        reiniciarRonda()
        reiniciarSecuencia()
        reiniciarSecuenciaUsuario()
        generarSecuencia()
        Datos.estado = Estado.INICIO
    }

    /**
     * Genera un número aleatorio de 0 a max
     * @param max El valor máximo para generar el número aleatorio
     * @return El número aleatorio generado
     */
    private fun generarNumeroAleatorio(max: Int): Int {
        Log.d("Dev", "Generando numero aleatorio...")
        return (0..max-1).random()
    }
    // 0 = Rojo, 1 = Amarillo, 2 = Verde, 3 = Azul

    /**
     * Genera una secuencia inicial para el juego
     */
    private fun generarSecuencia() {
        Log.d("Dev", "Generando secuencia...")
        // repeat Ejecuta un bloque de codigo X veces (en base al parámetro dado)
        Datos.secuencia.clear()
        aumentarSecuencia()
        Datos.estado = Estado.ESPERANDO
    }

    /**
     * Aumenta la secuencia del juego
     */
    private fun aumentarSecuencia() {
        Log.d("Dev", "Aumentando secuencia...")
        Datos.estado = Estado.SECUENCIA
        Datos.secuencia.add(traducirSecuencia(generarNumeroAleatorio(4)))
        Log.d("Dev", "Secuencia2: " + Datos.secuencia.toString())
        mostrarSecuencia()
    }

    /**
     * Traduce un número a un color específico
     * @param numero número a traducir en un color
     * @return color correspondiente al número
     */
    private fun traducirSecuencia(numero: Int): Colores {
        val claseColor = when (numero) {
            0 -> Colores.CLASE_ROJO
            1 -> Colores.CLASE_AMARILLO
            2 -> Colores.CLASE_VERDE
            3 -> Colores.CLASE_AZUL
            else -> Colores.CLASE_ROJO // UNREACHABLE
        }
        return claseColor
    }

    /**
     * Muestra la secuencia generada al usuario
     */
    private fun mostrarSecuencia(){
        viewModelScope.launch {
            Log.d("Dev", "Mostrando secuencia...")
            var i = 0
            while (i != Datos.ronda) {
                resaltarBoton(Datos.secuencia[i])
                delay(500) // Esperamos 2secs
                i++
            }
        }
    }

    /**
     * Resalta el botón correspondiente al color dado
     * @param colorin El color a resaltar en el botón
     */
    fun resaltarBoton(colorin: Colores) {
        viewModelScope.launch {
            Log.d("Dev","Cambiando color a blanco")
            val color_original = colorin.color.value // Guardamos el color del boton en una variable temporal
            colorin.color.value = Color.White // Cambiamos el color del boton a blanco
            Log.d("Dev","Esperandos...")
            delay(300) // Esperamos 2secs
            Log.d("Dev","Restaurando color original...")
            colorin.color.value = color_original // Restauramos el color original del boton
        }
    }

    /**
     * Aumenta la secuencia de entrada del usuario
     * @param secuencia La secuencia de entrada del usuario
     */
    fun aumentarSecuenciaUsuario(secuencia: Colores) {
        Datos.estado = Estado.ENTRADA
        Datos.secuenciaUsuario.add(secuencia)
        Log.d("Dev", "Aumentando secuencia de usuario...")
        Log.d("Dev", "Secuencia usuario: " + Datos.secuenciaUsuario.toString())
    }

    /**
     * Comprueba si la secuencia del usuario coincide con la secuencia generada.
     */
    fun comprobarSecuencia() {
        Log.d("Dev",  "Compobando...")
        Log.d("Dev", "Secuencia: " + Datos.secuencia.toString())
        Log.d("Dev", "Secuencia usuario: " + Datos.secuenciaUsuario.toString())
        Datos.estado = Estado.COMPROBANDO
        if (Datos.secuencia == Datos.secuenciaUsuario) { // Si son iguales
            Log.d("Dev", "Secuencia correcta")
            Datos.ronda++ // Aumentamos la ronda

            if (Datos.ronda > Datos.record) {
                Datos.record = Datos.ronda
            }
            reiniciarSecuenciaUsuario()
            aumentarSecuencia()
        } else {
            Log.d("Dev", "Secuencia incorrecta")
            reiniciarRonda()
            reiniciarSecuencia()
            reiniciarSecuenciaUsuario()
            Log.d("Dev", "Reiniciando")
            Log.d("Dev", "Secuencia: " + Datos.secuencia.toString())
            Log.d("Dev", "Secuencia usuario: " + Datos.secuenciaUsuario.toString())
            Datos.estado = Estado.FINAL
        }
    }

    private fun reiniciarRonda() { Datos.ronda = 1 } // Reinicia la ronda de vuelta a 1
    private fun reiniciarSecuencia() { Datos.secuencia.clear() } // Vacia la secuencia del programa
    private fun reiniciarSecuenciaUsuario() { Datos.secuenciaUsuario.clear() } // Vacia la secuencia del usuario
}