package com.example.dbgroupwork.Data.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import kotlinx.coroutines.flow.Flow

@Dao
interface ClasificacionDao {

    @Query("select * from ClasificacionRoom")
    suspend fun getAllClasificaciones(): Flow<List<ClasificacionLocal>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(clasificacion: ClasificacionLocal)

    @Query("select * from ClasificacionRoom where id = :id")
    suspend fun getClasificacionById(id: Int): ClasificacionLocal

    @Delete
    suspend fun deleteClasificacion(clasificacion: ClasificacionLocal)
}