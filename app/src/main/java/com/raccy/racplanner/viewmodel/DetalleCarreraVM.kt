package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raccy.racplanner.repository.DetalleCarreraRepository
import com.raccy.racplanner.network.response.DetalleCarreraResponse
import com.raccy.racplanner.network.response.GroupResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalleCarreraVM : ViewModel() {
    private val repository = DetalleCarreraRepository()
    private val _state = MutableStateFlow<DetalleCarreraResponse?>(null)
    private val _gruposSeleccionados = MutableStateFlow<Map<Int, GroupResponse>>(emptyMap())

    val gruposSeleccionados: StateFlow<Map<Int, GroupResponse>> = _gruposSeleccionados.asStateFlow()
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
    }
}