package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raccy.racplanner.repository.DetalleCarreraRepository
import com.raccy.racplanner.network.response.DetalleCarreraResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalleCarreraVM : ViewModel() {
    private val repository = DetalleCarreraRepository()
    private val _state = MutableStateFlow<DetalleCarreraResponse?>(null)

    val state: StateFlow<DetalleCarreraResponse?> = _state.asStateFlow()

    fun cargarCarrera(code: String) {
        _state.value = null
        viewModelScope.launch {
            _state.value = repository.getDetalleCarrera(code)
        }
    }
}