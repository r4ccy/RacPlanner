package com.raccy.racplanner.repository

import com.raccy.racplanner.network.ApiService
import com.raccy.racplanner.network.RetrofitClient
import com.raccy.racplanner.network.response.DetalleCarreraResponse

class DetalleCarreraRepository(
    private val cache: DetalleCarreraCacheRepository,
    private val api: ApiService = RetrofitClient.apiService
) {
    suspend fun getDetalleCarrera(
        code: String
    ): DetalleCarreraResponse {
        return try {
            val response = api.getDetalleCarrera(code)
            cache.guardar(
                code,
                response
            )
            response
        } catch (e: Exception) {
            cache.obtener(code)
                ?: throw e
        }
    }
}