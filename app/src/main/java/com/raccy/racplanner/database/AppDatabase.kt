package com.raccy.racplanner.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raccy.racplanner.dao.CarreraDao
import com.raccy.racplanner.dao.DetalleCarreraDao
import com.raccy.racplanner.dao.EventoHorarioDao
import com.raccy.racplanner.dao.GrupoSeleccionadoDao
import com.raccy.racplanner.entity.CarreraEntity
import com.raccy.racplanner.entity.DetalleCarreraEntity
import com.raccy.racplanner.entity.EventoHorarioEntity
import com.raccy.racplanner.entity.GrupoSeleccionadoEntity

@Database(
    entities = [
        GrupoSeleccionadoEntity::class,
        CarreraEntity::class,
        DetalleCarreraEntity::class,
        EventoHorarioEntity::class
    ],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun grupoSeleccionadoDao(): GrupoSeleccionadoDao
    abstract fun carreraDao(): CarreraDao
    abstract fun detalleCarreraDao(): DetalleCarreraDao
    abstract fun eventoHorarioDao(): EventoHorarioDao
}