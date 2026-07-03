package com.raccy.racplanner.mapper

import com.raccy.racplanner.model.EventoHorario
import com.raccy.racplanner.network.response.GroupResponse

fun GroupResponse.toEventosHorario(
    codigoMateria: Int,
    nombreMateria: String
): List<EventoHorario> {
    return schedule.map { horario ->
        EventoHorario(
            codigoMateria = codigoMateria,
            materia = nombreMateria,
            grupo = code,
            docente = teacher,
            dia = horario.day,
            inicio = horario.start,
            fin = horario.end,
            esTeoria = horario.isClass,
            aula = horario.room
        )
    }
}