package com.raccy.racplanner.network.response

import kotlinx.serialization.SerialName
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

    @SerialName ("updatet_at")
    val updatedAt: String
)