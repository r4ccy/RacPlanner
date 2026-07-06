package com.raccy.racplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrera")
data class CarreraEntity(
    @PrimaryKey
    val code: Int,
    val name: String,
    val semester: String,
    val path: String,
    val support: String,
    val url: String,
    val madeIn: String,
    val updatedAt: String
)