package com.raccy.racplanner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raccy.racplanner.entity.DetalleCarreraEntity

@Dao
interface DetalleCarreraDao {
    @Query("SELECT * FROM detalle_carrera WHERE codigo = :codigo")
    suspend fun obtener(
        codigo: String
    ): DetalleCarreraEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(
        detalle: DetalleCarreraEntity
    )

    @Query("DELETE FROM detalle_carrera")
    suspend fun eliminarTodos()
}