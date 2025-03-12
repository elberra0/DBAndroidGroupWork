package com.example.dbgroupwork.data

import com.example.dbgroupwork.data.local.PlanDatabaseDatasource
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.Domain.PlanRepository
import kotlinx.coroutines.flow.first

class PlanRepositoryImpl(private val planDatabaseDatasource: PlanDatabaseDatasource) :PlanRepository {
    override suspend fun insertPlan(plan: Plan) {
        planDatabaseDatasource.insertPlan(plan)
    }

    override suspend fun getAllPlan(): List<Plan> {
        val localPlanes = planDatabaseDatasource.getAllPlan().first()
        return localPlanes
    }
/*
    override suspend fun update(plan: Plan) {
        planDatabaseDatasource.update(plan)
    }

    override suspend fun delete(plan: Plan) {
        planDatabaseDatasource.delete(plan)
    }
*/
    override suspend fun getPlanById(planId: Int): Plan? {
        return planDatabaseDatasource.getPlanById(planId)
    }
}