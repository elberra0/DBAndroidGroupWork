package com.example.dbgroupwork.data.local.room

import com.example.dbgroupwork.data.local.PlanDatabaseDatasource
import com.example.dbgroupwork.data.toPlan
import com.example.dbgroupwork.data.toPlanLocal
import com.example.dbgroupwork.Domain.Models.Plan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlanRoomLocalDatasourceImpl(private val dao: PlanDao): PlanDatabaseDatasource {
    override suspend fun insertPlan(plan: Plan) {
        dao.insertPlan(plan.toPlanLocal())
    }

    override fun getAllPlan(): Flow<List<Plan>> {
      return dao.getAllPlan().map { planes ->  planes.map { it.toPlan() } }
    }

    override suspend fun getPlanById(planId: Int): Plan? {
       return dao.getPlanById(planId)?.toPlan()
    }
}