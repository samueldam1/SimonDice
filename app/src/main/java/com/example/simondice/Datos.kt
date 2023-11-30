package com.example.simondice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Clase tipo Objeto, con modelo Singleton, en el que guardaremos los datos de la aplicacion
 * @property ronda Numero de ronda actual
 * @property secuencia Secuencia de colores
 * @property secuenciaUsuario Secuencia de colores introducidos por el usuario
 * @property record Record de puntuacion
 * @property estado Estado del juego
 */
object Datos {
    var ronda = 1
    var record = 0
    var secuencia = mutableListOf<Colores>()
    var secuenciaUsuario = mutableListOf<Colores>()
    var estado = Estado.INICIO
}

/**
 * Enum con los estados del juego
 */
enum class Estado {
    INICIO,
    SECUENCIA,
    ESPERANDO,
    ENTRADA,
    COMPROBANDO,
    FINAL
}

/**
 * Enum con los colores utilizados
 */
enum class Colores(val color: MutableState<Color>, val txt: String) {
    CLASE_ROJO(color = mutableStateOf(Color.Red), txt = "rojo"),
    CLASE_VERDE(color = mutableStateOf(Color.Green), txt = "verde"),
    CLASE_AZUL(color = mutableStateOf(Color.Blue), txt = "azul"),
    CLASE_AMARILLO(color = mutableStateOf(Color.Yellow), txt = "amarillo"),
}