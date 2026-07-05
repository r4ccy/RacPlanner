package com.raccy.racplanner.network.response

import kotlinx.serialization.Serializable

@Serializable
data class CarreraResponse(
    val madeIn: String,
    val semester: String,
    val support: String,
    val path: String,
    val code: Int,
    val name: String,
    val url: String,
    val updatet_at: String
)