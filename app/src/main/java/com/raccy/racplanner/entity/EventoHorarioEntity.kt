package com.raccy.racplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evento_horario")
data class EventoHorarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val codigoMateria: Int,
    val materia: String,
    val grupo: String,
    val docente: String,
    val dia: String,
    val inicio: String,
    val fin: String,
    val esTeoria: Boolean,
    val aula: String,
    val codigoCarrera: String,
    val nombreCarrera: String
)