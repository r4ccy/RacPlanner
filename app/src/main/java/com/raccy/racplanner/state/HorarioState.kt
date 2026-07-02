package com.raccy.racplanner.state

import com.raccy.racplanner.mapper.toEventosHorario
import com.raccy.racplanner.model.EventoHorario
import com.raccy.racplanner.network.response.GroupResponse
import com.raccy.racplanner.utils.overlaps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HorarioState {

    private val _eventos =
        MutableStateFlow<List<EventoHorario>>(emptyList())

    private val _gruposSeleccionados = MutableStateFlow<Map<Int, GroupResponse>>(emptyMap())
    val eventos: StateFlow<List<EventoHorario>> = _eventos.asStateFlow()
    val gruposSeleccionados: StateFlow<Map<Int, GroupResponse>> = _gruposSeleccionados.asStateFlow()
    private fun agregarGrupo(
        codigoMateria: Int,
        nombreMateria: String,
        grupo: GroupResponse
    ) {
        val eventosActuales = _eventos.value.filter { it.codigoMateria != codigoMateria }
        val nuevosEventos = grupo.toEventosHorario(
            codigoMateria,
            nombreMateria
        )
        _eventos.value = eventosActuales + nuevosEventos
        recalcularColisiones()
    }

    private fun quitarGrupo(codigoMateria: Int) {
        _gruposSeleccionados.value = _gruposSeleccionados.value - codigoMateria
        _eventos.value = _eventos.value.filter {
            it.codigoMateria != codigoMateria
        }
        recalcularColisiones()
    }

    fun seleccionarGrupo(
        codigoMateria: Int,
        nombreMateria: String,
        grupo: GroupResponse
    ) {
        val grupoActual = _gruposSeleccionados.value[codigoMateria]
        if (grupoActual?.code == grupo.code) {
            quitarGrupo(codigoMateria)
        } else {
            _gruposSeleccionados.value = _gruposSeleccionados.value + (codigoMateria to grupo)
            agregarGrupo(
                codigoMateria,
                nombreMateria,
                grupo
            )
        }
    }

    fun vaciarHorario() {
        _gruposSeleccionados.value = emptyMap()
        _eventos.value = emptyList()
    }

    private fun recalcularColisiones() {
        val eventos = _eventos.value
        val eventosConColision = mutableSetOf<EventoHorario>()
        for (evento in eventos) {
            for (otroEvento in eventos) {
                if (evento == otroEvento) {
                    continue
                }
                    if (overlaps(evento, otroEvento)) {
                        eventosConColision.add(evento)
                        eventosConColision.add(otroEvento)
                    }
            }
        }
        _eventos.value = eventos.map { evento ->

            evento.copy(
                tieneColision = eventosConColision.contains(evento)
            )

        }
    }
}