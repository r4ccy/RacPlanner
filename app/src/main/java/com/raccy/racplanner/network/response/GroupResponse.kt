package com.raccy.racplanner.network.response

import kotlinx.serialization.Serializable

@Serializable
data class GroupResponse(
    val code: String,
    val teacher: String,
    val schedule: List<ScheduleResponse>
)
