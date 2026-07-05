package com.raccy.racplanner.repository

import com.raccy.racplanner.dao.CarreraDao
import com.raccy.racplanner.entity.CarreraEntity

class CarreraCacheRepository(
    private val dao: CarreraDao
) {
    suspend fun guardarCarreras(
        carreras: List<CarreraEntity>
    ) {
        dao.eliminarTodas()
        dao.guardarTodas(carreras)
    }

    suspend fun obtenerCarreras(): List<CarreraEntity> {
        return dao.obtenerTodas()
    }
}