package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raccy.racplanner.repository.CarreraRepository
import com.raccy.racplanner.state.BuscadorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BuscadorVM : ViewModel() {
    private val _state = MutableStateFlow(BuscadorState())
    val state: StateFlow<BuscadorState> = _state.asStateFlow()

    private val repository = CarreraRepository()

    init {
        viewModelScope.launch {
            val carreras = repository.getCarreras()
            _state.value = _state.value.copy(carreras = carreras)
        }
    }
}