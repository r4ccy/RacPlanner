package com.raccy.racplanner.mapper

import com.raccy.racplanner.network.response.DetalleCarreraResponse
import com.raccy.racplanner.network.response.GroupResponse
import com.raccy.racplanner.network.response.SubjectResponse

fun DetalleCarreraResponse.buscarMateria(
    codigoMateria: Int
): SubjectResponse? {

    return levels
        .flatMap { it.subjects }
        .firstOrNull { it.code == codigoMateria }

}

fun DetalleCarreraResponse.buscarGrupo(
    codigoMateria: Int,
    codigoGrupo: String
): GroupResponse? {
    val materia = buscarMateria(codigoMateria)
        ?: return null

    return materia.groups.firstOrNull {
        it.code == codigoGrupo
    }

}