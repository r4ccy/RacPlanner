package com.raccy.racplanner.state

import com.raccy.racplanner.mapper.toEventosHorario
import com.raccy.racplanner.model.EventoHorario
import com.raccy.racplanner.network.response.GroupResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HorarioState {

    private val _eventos =
        MutableStateFlow<List<EventoHorario>>(emptyList())

    val eventos: StateFlow<List<EventoHorario>> = _eventos.asStateFlow()

    fun agregarGrupo(
        nombreMateria: String,
        grupo: GroupResponse
    ) {
        _eventos.value += grupo.toEventosHorario(nombreMateria)
        println("Eventos: ${_eventos.value.size}")
    }

    fun vaciarHorario() {
        _eventos.value = emptyList()
    }

    fun obtenerEventos(): List<EventoHorario> {
        return _eventos.value
    }
}