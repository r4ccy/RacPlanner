package com.raccy.racplanner.mapper

import com.raccy.racplanner.model.Carrera
import com.raccy.racplanner.network.response.CarreraResponse

fun CarreraResponse.toCarrera(): Carrera {
    return Carrera(
        codigo = code.toString(),
        nombre = name,
        semestre = semester
    )
}