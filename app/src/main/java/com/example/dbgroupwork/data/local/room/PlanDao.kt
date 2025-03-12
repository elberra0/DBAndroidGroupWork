package com.example.dbgroupwork.data.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Query("select * from PlanRoom where id = :id")
    suspend fun getPlanById(id: Int): PlanLocal?

    @Query("select * from PlanRoom")
    fun getAllPlan(): Flow<List<PlanLocal>>

    @Insert(onConflict = IGNORE)
    suspend fun insertPlan(plan:PlanLocal)
}