package com.raccy.racplanner.repository

import com.raccy.racplanner.mapper.toCarrera
import com.raccy.racplanner.mapper.toEntity
import com.raccy.racplanner.mapper.toResponse
import com.raccy.racplanner.model.Carrera
import com.raccy.racplanner.network.ApiService
import com.raccy.racplanner.network.RetrofitClient

class CarreraRepository(
    private val cache: CarreraCacheRepository,
    private val api: ApiService = RetrofitClient.apiService
) {
    suspend fun getCarreras(): List<Carrera> {
        return try {
            val response = api.getCarreras()
            cache.guardarCarreras(
                response.map { it.toEntity() }
            )
            response.map { it.toCarrera() }
        } catch (_: Exception) {
            cache.obtenerCarreras().map {
                it.toResponse().toCarrera()
            }
        }
    }
}