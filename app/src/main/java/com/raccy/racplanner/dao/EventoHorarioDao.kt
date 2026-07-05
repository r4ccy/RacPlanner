package com.raccy.racplanner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raccy.racplanner.entity.EventoHorarioEntity

@Dao
interface EventoHorarioDao {

    @Query("SELECT * FROM evento_horario")
    suspend fun obtenerTodos(): List<EventoHorarioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarTodos(
        eventos: List<EventoHorarioEntity>
    )

    @Query("DELETE FROM evento_horario")
    suspend fun eliminarTodos()

}