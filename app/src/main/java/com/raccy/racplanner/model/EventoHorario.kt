package com.raccy.racplanner.model

data class EventoHorario(
    val materia: String,
    val grupo: String,
    val docente: String,
    val dia: String,
    val inicio: String,
    val fin: String,
    val esTeoria: Boolean
)
