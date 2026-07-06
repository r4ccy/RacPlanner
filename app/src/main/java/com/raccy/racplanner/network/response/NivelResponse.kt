package com.raccy.racplanner.network.response

import kotlinx.serialization.Serializable

@Serializable
data class NivelResponse(
    val code: String,
    val subjects: List<SubjectResponse>
)
