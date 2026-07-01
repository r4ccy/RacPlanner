package com.raccy.racplanner.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    val day: String,
    val start: String,
    val end: String,
    val room: String,
    val teacher: String,
    val isClass: Boolean
)
