package com.raccy.racplanner.utils

import com.raccy.racplanner.model.EventoHorario

val ordenDias = listOf(
    "LU",
    "MA",
    "MI",
    "JU",
    "VI",
    "SA"
)

val horasGrilla = listOf(
    "645",
    "730",
    "815",
    "900",
    "945",
    "1030",
    "1115",
    "1200",
    "1245",
    "1330",
    "1415",
    "1500",
    "1545",
    "1630",
    "1715",
    "1800",
    "1845",
    "1930",
    "2015",
    "2100",
    "2145"
)

fun overlaps(
    dia1: String,
    inicio1: String,
    fin1: String,
    dia2: String,
    inicio2: String,
    fin2: String
): Boolean {

    if (dia1 != dia2) {
        return false
    }

    val inicioHora1 = horaEnNumero(inicio1)
    val finHora1 = horaEnNumero(fin1)
    val inicioHora2 = horaEnNumero(inicio2)
    val finHora2 = horaEnNumero(fin2)

    return inicioHora1 < finHora2 &&
            inicioHora2 < finHora1
}

fun overlaps(
    evento1: EventoHorario,
    evento2: EventoHorario
): Boolean {
    return overlaps(
        evento1.dia,
        evento1.inicio,
        evento1.fin,
        evento2.dia,
        evento2.inicio,
        evento2.fin
    )
}

private fun horaEnNumero(
    hora: String
): Int {
    return hora.toInt()
}