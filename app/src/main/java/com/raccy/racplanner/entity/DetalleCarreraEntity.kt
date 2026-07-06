package com.raccy.racplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detalle_carrera")
data class DetalleCarreraEntity(
    @PrimaryKey
    val codigo: String,
    val json: String
)