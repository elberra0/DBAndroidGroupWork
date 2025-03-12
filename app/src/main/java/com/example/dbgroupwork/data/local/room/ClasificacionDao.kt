package com.example.dbgroupwork.data.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClasificacionDao {

    @Query("select * from ClasificacionRoom where id = :id")
    suspend fun getById(id: Int): ClasificacionLocal?

    @Query("select * from ClasificacionRoom")
    fun getAll(): Flow<List<ClasificacionLocal>>
}