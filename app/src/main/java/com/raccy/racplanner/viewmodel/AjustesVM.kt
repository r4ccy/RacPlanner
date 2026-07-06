package com.raccy.racplanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raccy.racplanner.datastore.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AjustesVM(
    application: Application
) : AndroidViewModel(application) {

    val temaOscuro = SettingsDataStore
        .temaOscuro(getApplication())
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    fun cambiarTema(
        oscuro: Boolean
    ) {
        viewModelScope.launch {
            SettingsDataStore.guardarTema(
                getApplication(),
                oscuro
            )
        }
    }
}