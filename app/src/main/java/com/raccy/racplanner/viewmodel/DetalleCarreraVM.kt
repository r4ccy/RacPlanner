package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raccy.racplanner.repository.DetalleCarreraRepository
import com.raccy.racplanner.network.response.DetalleCarreraResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalleCarreraVM(
    private val repository: DetalleCarreraRepository
) : ViewModel() {
    private val _state = MutableStateFlow<DetalleCarreraResponse?>(null)
    private val _error = MutableStateFlow<String?>(null)

    val state: StateFlow<DetalleCarreraResponse?> = _state.asStateFlow()
    val error: StateFlow<String?> = _error.asStateFlow()

    fun cargarCarrera(code: String) {
        _state.value = null
        _error.value = null
        viewModelScope.launch {
            try {
                _state.value = repository.getDetalleCarrera(code)
            } catch (_: Exception) {
                _error.value =
                    "Esta carrera no está disponible sin conexión a internet :("

            }
        }
    }
}