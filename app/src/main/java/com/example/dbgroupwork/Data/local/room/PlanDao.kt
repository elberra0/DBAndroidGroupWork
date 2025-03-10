package com.example.dbgroupwork.Data.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao:BaseDao<PlanLocal> {
    @Query("select * from PlanRoom where id = :id")
    suspend fun getById(id: Int): PlanLocal?

    @Query("select * from PlanRoom")
    override suspend fun getAll(): Flow<List<PlanLocal>>

    @Transaction
    @Query("select * from  PlanRoom where id = :id")
    suspend fun getPlanConClasificacionById(id: Int): PlanConClasificacion?

    @Transaction
    @Query("select * from PlanRoom")
    suspend fun getAllPlanesConClasificacion(): List<PlanConClasificacion>
}