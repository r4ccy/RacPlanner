package com.raccy.racplanner.repository

import com.raccy.racplanner.network.ApiService
import com.raccy.racplanner.network.RetrofitClient
import com.raccy.racplanner.network.response.DetalleCarreraResponse

class DetalleCarreraRepository(
    private val api: ApiService = RetrofitClient.apiService
) {
    suspend fun getDetalleCarrera(code: String): DetalleCarreraResponse {
        return api.getDetalleCarrera(code)
    }
}