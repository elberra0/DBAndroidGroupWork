package com.example.dbgroupwork.Data.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {

    @Query("select * from PlanRoom")
    suspend fun getAllPlan(): Flow<List<PlanLocal>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(clasificacion: PlanLocal)

    @Query("select * from ClasificacionRoom where id = :id")
    suspend fun getClasificacionById(id: Int): PlanLocal

    @Transaction
    @Query("select * from  PlanRoom where id = :id")
    suspend fun getPlanConClasificacionById(id: Int): PlanConClasificacion?

    @Transaction
    @Query("select * from PlanRoom")
    suspend fun getAllPlanesConClasificacion(): List<PlanConClasificacion>

    @Delete
    suspend fun deleteClasificacion(plan: PlanLocal)
}