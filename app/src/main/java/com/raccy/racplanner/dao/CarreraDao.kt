package com.raccy.racplanner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raccy.racplanner.entity.CarreraEntity

@Dao
interface CarreraDao {
    @Query("SELECT * FROM carrera ORDER BY name ASC")
    suspend fun obtenerTodas(): List<CarreraEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarTodas(
        carreras: List<CarreraEntity>
    )

    @Query("DELETE FROM carrera")
    suspend fun eliminarTodas()
}