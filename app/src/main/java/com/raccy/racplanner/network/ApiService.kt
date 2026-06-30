package com.raccy.racplanner.network

import retrofit2.http.GET
import com.raccy.racplanner.model.Carrera
import com.raccy.racplanner.network.response.CarreraResponse
import com.raccy.racplanner.network.response.DetalleCarreraResponse
import retrofit2.http.Path

interface ApiService {
    @GET("index.json")
    suspend fun getCarreras(): List<CarreraResponse>
    @GET("2026-01/{code}.json")
    suspend fun getDetalleCarrera(@Path("code") code: String): DetalleCarreraResponse
}