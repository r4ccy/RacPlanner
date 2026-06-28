package com.raccy.racplanner.network

import retrofit2.http.GET
import com.raccy.racplanner.model.Carrera

interface ApiService {
    @GET("index.json")
    suspend fun getCarreras(): List<Carrera>
}