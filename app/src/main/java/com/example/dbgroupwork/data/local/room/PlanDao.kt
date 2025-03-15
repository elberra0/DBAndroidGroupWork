package com.example.dbgroupwork.data.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Query("select * from PlanRoom")
    fun getAllPlan(): Flow<List<PlanLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(planes: List<PlanLocal>)

    @Query("select * from PlanRoom where id = :id")
    suspend fun getPlanById(id: Int): PlanLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanById(plan: PlanLocal)

}