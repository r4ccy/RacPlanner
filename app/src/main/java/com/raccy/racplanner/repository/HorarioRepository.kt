package com.raccy.racplanner.repository

import com.raccy.racplanner.dao.GrupoSeleccionadoDao
import com.raccy.racplanner.entity.GrupoSeleccionadoEntity

class HorarioRepository(
    private val dao: GrupoSeleccionadoDao
) {
    suspend fun guardarGrupo(
        grupo: GrupoSeleccionadoEntity
    ) {
        dao.guardar(grupo)
    }

    suspend fun obtenerGruposSeleccionados(): List<GrupoSeleccionadoEntity> {
        return dao.obtenerTodos()
    }

    suspend fun eliminarTodos() {
        dao.eliminarTodos()
    }
}