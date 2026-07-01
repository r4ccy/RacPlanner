package com.raccy.racplanner.network.response

import kotlinx.serialization.Serializable

@Serializable
data class DetalleCarreraResponse(
    val code : Int,
    val name : String,
    val semester : String,
    val levels : List<NivelResponse>
)