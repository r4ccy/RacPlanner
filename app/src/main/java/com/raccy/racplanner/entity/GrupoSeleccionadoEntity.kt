package com.raccy.racplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grupo_seleccionado")
data class GrupoSeleccionadoEntity(
    @PrimaryKey
    val codigoMateria: Int,
    val codigoCarrera: String,
    val nombreCarrera: String,
    val nombreMateria: String,
    val codigoGrupo: String
)