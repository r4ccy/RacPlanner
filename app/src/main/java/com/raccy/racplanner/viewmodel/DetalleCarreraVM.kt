package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raccy.racplanner.repository.DetalleCarreraRepository
import com.raccy.racplanner.network.response.DetalleCarreraResponse
import com.raccy.racplanner.network.response.GroupResponse
import com.raccy.racplanner.utils.overlaps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalleCarreraVM : ViewModel() {
    private val repository = DetalleCarreraRepository()
    private val _state = MutableStateFlow<DetalleCarreraResponse?>(null)
    private val _gruposSeleccionados = MutableStateFlow<Map<Int, GroupResponse>>(emptyMap())
    private val _hayColision = MutableStateFlow(false)

    val gruposSeleccionados: StateFlow<Map<Int, GroupResponse>> = _gruposSeleccionados.asStateFlow()
    val hayColision: StateFlow<Boolean> = _hayColision.asStateFlow()
    val state: StateFlow<DetalleCarreraResponse?> = _state.asStateFlow()

    fun cargarCarrera(code: String) {
        _state.value = null
        viewModelScope.launch {
            _state.value = repository.getDetalleCarrera(code)
        }
    }

    fun seleccionarGrupo(
        codigoMateria: Int,
        grupo: GroupResponse
    ) {
        _gruposSeleccionados.value = _gruposSeleccionados.value + (codigoMateria to grupo)
        _hayColision.value = verificarColisiones(codigoMateria, grupo)
    }

    private fun verificarColisiones(
        codigoMateria: Int,
        nuevoGrupo: GroupResponse
    ): Boolean {
        for ((materiaSeleccionada, grupoSeleccionado) in _gruposSeleccionados.value){
            if (materiaSeleccionada == codigoMateria) {
                continue
            }
            for (horarioNuevo in nuevoGrupo.schedule) {
                for (horarioSeleccionado in grupoSeleccionado.schedule) {
                    if (overlaps(horarioNuevo, horarioSeleccionado)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}