package com.raccy.racplanner.state

import com.raccy.racplanner.entity.GrupoSeleccionadoEntity
import com.raccy.racplanner.mapper.buscarGrupo
import com.raccy.racplanner.mapper.toEventosHorario
import com.raccy.racplanner.model.EventoHorario
import com.raccy.racplanner.network.response.GroupResponse
import com.raccy.racplanner.repository.DetalleCarreraCacheRepository
import com.raccy.racplanner.repository.HorarioRepository
import com.raccy.racplanner.utils.overlaps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HorarioState(
    private val repository: HorarioRepository,
    private val detalleCache: DetalleCarreraCacheRepository
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _eventos =
        MutableStateFlow<List<EventoHorario>>(emptyList())
    private val _carreras = MutableStateFlow<Map<String, String>>(emptyMap())
    private val _gruposSeleccionados = MutableStateFlow<Map<Int, GroupResponse>>(emptyMap())
    private val _nombresMaterias = MutableStateFlow<Map<Int, String>>(emptyMap())
    val eventos: StateFlow<List<EventoHorario>> = _eventos.asStateFlow()
    val carreras = _carreras.asStateFlow()
    val gruposSeleccionados: StateFlow<Map<Int, GroupResponse>> = _gruposSeleccionados.asStateFlow()
    val nombresMaterias = _nombresMaterias.asStateFlow()

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
        _gruposSeleccionados.value -= codigoMateria
        _nombresMaterias.value -= codigoMateria
        _eventos.value = _eventos.value.filter {
            it.codigoMateria != codigoMateria
        }
        recalcularColisiones()
    }

    fun seleccionarGrupo(
        codigoCarrera: String,
        nombreCarrera: String,
        codigoMateria: Int,
        nombreMateria: String,
        grupo: GroupResponse
    ) {
        val grupoActual = _gruposSeleccionados.value[codigoMateria]
        if (grupoActual?.code == grupo.code) {
            quitarGrupo(codigoMateria)
        } else {
            aplicarGrupo(
                codigoCarrera,
                nombreCarrera,
                codigoMateria,
                nombreMateria,
                grupo
            )
            scope.launch {
                repository.guardarGrupo(
                    GrupoSeleccionadoEntity(
                        codigoMateria = codigoMateria,
                        codigoCarrera = codigoCarrera,
                        nombreCarrera = nombreCarrera,
                        nombreMateria = nombreMateria,
                        codigoGrupo = grupo.code
                    )
                )
            }
        }
    }

    private fun aplicarGrupo(
        codigoCarrera: String,
        nombreCarrera: String,
        codigoMateria: Int,
        nombreMateria: String,
        grupo: GroupResponse
    ) {
        _gruposSeleccionados.value += codigoMateria to grupo
        _nombresMaterias.value += codigoMateria to nombreMateria
        _carreras.value += codigoCarrera to nombreCarrera
        agregarGrupo(
            codigoMateria,
            nombreMateria,
            grupo
        )
    }

    fun vaciarHorario() {
        _gruposSeleccionados.value = emptyMap()
        _nombresMaterias.value = emptyMap()
        _carreras.value = emptyMap()
        _eventos.value = emptyList()

        scope.launch {
            repository.eliminarTodos()
        }
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

    fun cargarHorario() {
        scope.launch {

            repository.obtenerGruposSeleccionados().forEach { guardado ->

                val detalle = detalleCache.obtener(
                    guardado.codigoCarrera
                ) ?: return@forEach

                val grupo = detalle.buscarGrupo(
                    guardado.codigoMateria,
                    guardado.codigoGrupo
                ) ?: return@forEach

                aplicarGrupo(
                    codigoCarrera = guardado.codigoCarrera,
                    nombreCarrera = guardado.nombreCarrera,
                    codigoMateria = guardado.codigoMateria,
                    nombreMateria = guardado.nombreMateria,
                    grupo = grupo
                )
            }
        }
    }
}