package com.raccy.racplanner.mapper

import com.raccy.racplanner.entity.CarreraEntity
import com.raccy.racplanner.model.Carrera
import com.raccy.racplanner.network.response.CarreraResponse

fun CarreraResponse.toCarrera(): Carrera {
    return Carrera(
        codigo = code.toString(),
        nombre = name,
        semestre = semester
    )
}

fun CarreraResponse.toEntity(): CarreraEntity {
    return CarreraEntity(
        code = code,
        name = name,
        semester = semester,
        path = path,
        support = support,
        url = url,
        madeIn = madeIn,
        updatedAt = updatedAt
    )
}

fun CarreraEntity.toResponse(): CarreraResponse {
    return CarreraResponse(
        code = code,
        name = name,
        semester = semester,
        path = path,
        support = support,
        url = url,
        madeIn = madeIn,
        updatedAt = updatedAt
    )
}