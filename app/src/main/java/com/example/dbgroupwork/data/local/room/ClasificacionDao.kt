package com.example.dbgroupwork.data.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClasificacionDao {

    @Query("select * from ClasificacionRoom")
    fun getAllClasificacion(): Flow<List<ClasificacionLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasificacion(clasificaciones: List<ClasificacionLocal>)

    @Query("select * from ClasificacionRoom where id = :id")
    suspend fun getClasificacionById(id: Int): ClasificacionLocal
}