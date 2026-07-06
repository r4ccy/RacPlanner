package com.raccy.racplanner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raccy.racplanner.entity.GrupoSeleccionadoEntity

@Dao
interface GrupoSeleccionadoDao {
    @Query("SELECT * FROM grupo_seleccionado")
    suspend fun obtenerTodos(): List<GrupoSeleccionadoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(grupo: GrupoSeleccionadoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarTodos(
        grupos: List<GrupoSeleccionadoEntity>
    )

    @Query("DELETE FROM grupo_seleccionado")
    suspend fun eliminarTodos()
}