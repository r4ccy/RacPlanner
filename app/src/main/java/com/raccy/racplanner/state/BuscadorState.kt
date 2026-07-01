package com.raccy.racplanner.state

import com.raccy.racplanner.model.Carrera

data class BuscadorState(
    val carreras: List<Carrera> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)