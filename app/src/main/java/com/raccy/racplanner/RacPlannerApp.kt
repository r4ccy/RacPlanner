package com.raccy.racplanner

import android.app.Application
import com.raccy.racplanner.database.DatabaseProvider
import com.raccy.racplanner.repository.CarreraCacheRepository
import com.raccy.racplanner.repository.DetalleCarreraCacheRepository
import com.raccy.racplanner.repository.HorarioRepository

class RacPlannerApp : Application() {

    lateinit var horarioRepository: HorarioRepository
        private set

    lateinit var carreraCacheRepository: CarreraCacheRepository
        private set

    lateinit var detalleCarreraCacheRepository: DetalleCarreraCacheRepository
        private set

    override fun onCreate() {
        super.onCreate()

        val database = DatabaseProvider.getDatabase(this)

        carreraCacheRepository = CarreraCacheRepository(
            database.carreraDao()
        )
        detalleCarreraCacheRepository = DetalleCarreraCacheRepository(
            database.detalleCarreraDao()
        )
        horarioRepository = HorarioRepository(
            database.grupoSeleccionadoDao()
        )
    }
}