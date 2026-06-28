package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import com.raccy.racplanner.data.carreras
import com.raccy.racplanner.state.BuscadorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BuscadorVM : ViewModel() {
    private val _state = MutableStateFlow(
        BuscadorState(
            carreras = carreras
        )
    )

    val state: StateFlow<BuscadorState> = _state.asStateFlow()
}