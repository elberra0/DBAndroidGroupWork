package com.example.dbgroupwork.Domain

import com.example.dbgroupwork.Domain.Models.Plan
import kotlinx.coroutines.flow.Flow

interface FitAppRepository {
    suspend fun getAllPlan(): List<Plan>
    suspend fun getPlanById(planId: Int): Plan?
}