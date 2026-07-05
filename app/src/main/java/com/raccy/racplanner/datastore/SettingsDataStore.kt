package com.raccy.racplanner.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

object SettingsDataStore {

    private val DARK_THEME = booleanPreferencesKey("dark_theme")

    fun temaOscuro(context: Context) =
        context.dataStore.data.map { preferences ->
            preferences[DARK_THEME] ?: false
        }

    suspend fun guardarTema(
        context: Context,
        oscuro: Boolean
    ) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME] = oscuro
        }
    }
}