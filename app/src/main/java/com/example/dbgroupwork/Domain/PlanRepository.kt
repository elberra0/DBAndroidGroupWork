package com.example.dbgroupwork.Domain

import com.example.dbgroupwork.Domain.Models.Plan
import kotlinx.coroutines.flow.Flow

interface PlanRepository {
    suspend fun insertPlan(plan: Plan)
    suspend fun getAllPlan(): List<Plan>
//    suspend fun update(plan: Plan)
//    suspend fun delete(plan: Plan)
    suspend fun getPlanById(planId: Int): Plan?
}