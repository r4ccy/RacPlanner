package com.raccy.racplanner.network.response

import kotlinx.serialization.Serializable

@Serializable
data class SubjectResponse(
    val code : Int,
    val name : String,
    val groups : List<GroupResponse>
)
