package com.raccy.racplanner.network

import retrofit2.http.GET
import com.raccy.racplanner.model.Carrera
import com.raccy.racplanner.network.response.CarreraResponse

interface ApiService {
    @GET("index.json")
    suspend fun getCarreras(): List<CarreraResponse>
}