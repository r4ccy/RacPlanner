package com.raccy.racplanner.network.response

data class SubjectResponse(
    val code : Int,
    val name : String,
    val groups : List<GroupResponse>
)
