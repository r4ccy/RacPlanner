package com.raccy.racplanner.utils

fun formatearDia(dia: String): String {
    return when (dia) {
        "LU" -> "Lunes"
        "MA" -> "Martes"
        "MI" -> "Miércoles"
        "JU" -> "Jueves"
        "VI" -> "Viernes"
        else -> dia
    }
}

fun formatearHora(hora: String): String {
    return hora.padStart(4, '0').let {
        "${it.substring(0, 2)}:${it.substring(2)}"
    }
}