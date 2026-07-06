package com.raccy.racplanner.repository

import com.raccy.racplanner.dao.DetalleCarreraDao
import com.raccy.racplanner.entity.DetalleCarreraEntity
import com.raccy.racplanner.network.response.DetalleCarreraResponse
import kotlinx.serialization.json.Json

class DetalleCarreraCacheRepository(
    private val dao: DetalleCarreraDao
) {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun guardar(
        codigo: String,
        detalle: DetalleCarreraResponse
    ) {
        dao.guardar(
            DetalleCarreraEntity(
                codigo = codigo,
                json = json.encodeToString(detalle)
            )
        )
    }

    suspend fun obtener(
        codigo: String
    ): DetalleCarreraResponse? {
        val entity = dao.obtener(codigo)
            ?: return null

        return json.decodeFromString(entity.json)
    }

    suspend fun limpiar() {
        dao.eliminarTodos()
    }
}