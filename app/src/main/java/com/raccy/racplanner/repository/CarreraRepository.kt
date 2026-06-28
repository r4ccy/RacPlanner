package com.raccy.racplanner.repository

import com.raccy.racplanner.network.RetrofitClient
import com.raccy.racplanner.model.Carrera
import com.raccy.racplanner.network.ApiService

class CarreraRepository(
    private val api : ApiService = RetrofitClient.apiService
) {
    suspend fun getCarreras(): List<Carrera> {
        return api.getCarreras()
    }
}