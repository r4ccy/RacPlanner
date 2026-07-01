package com.raccy.racplanner.utils

import com.raccy.racplanner.network.response.ScheduleResponse

fun overlaps(
    horario1: ScheduleResponse,
    horario2: ScheduleResponse
): Boolean {
    if (horario1.day != horario2.day) {
        return false
    }

    val inicio1 = horaEnNumero(horario1.start)
    val fin1 = horaEnNumero(horario1.end)
    val inicio2 = horaEnNumero(horario2.start)
    val fin2 = horaEnNumero(horario2.end)
    return inicio1 < fin2 && inicio2 < fin1
}

private fun horaEnNumero(
    hora: String
): Int {
    return hora.toInt()
}