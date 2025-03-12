package com.example.dbgroupwork.Data.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClasificacionDao: BaseDao<ClasificacionLocal> {

    @Query("select * from ClasificacionRoom where id = :id")
    suspend fun getById(id: Int): ClasificacionLocal?

    @Query("select * from ClasificacionRoom")
    override suspend fun getAll(): Flow<List<ClasificacionLocal>>
}