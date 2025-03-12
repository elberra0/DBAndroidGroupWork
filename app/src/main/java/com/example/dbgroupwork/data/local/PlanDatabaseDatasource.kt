package com.example.dbgroupwork.data.local

import com.example.dbgroupwork.Domain.Models.Plan
import kotlinx.coroutines.flow.Flow

interface PlanDatabaseDatasource {
    suspend fun insertPlan(plan: Plan)
    fun getAllPlan(): Flow<List<Plan>>
    suspend fun getPlanById(planId: Int): Plan?
}