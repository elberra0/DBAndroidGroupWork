package com.example.dbgroupwork.data.local.room

import com.example.dbgroupwork.Data.toClasificacion
import com.example.dbgroupwork.Data.toClasificacionLocal
import com.example.dbgroupwork.Data.toPlan
import com.example.dbgroupwork.Data.toPlanLocal
import com.example.dbgroupwork.Domain.Models.Clasificacion
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.data.local.FitAppDatabaseDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class FitAppRoomLocalDatasourceImpl(private val planDao: PlanDao, private  val clasificacionDao: ClasificacionDao): FitAppDatabaseDatasource {
    override suspend fun insertPlan(planes: List<Plan>) {
        planDao.insertPlan(planes.map{ it.toPlanLocal()})
    }

    override fun getAllPlan(): Flow<List<Plan>> {
      return planDao.getAllPlan().map { planes ->  planes.map { it.toPlan() } }
    }

    override suspend fun getPlanById(planId: Int): Plan {
       return planDao.getPlanById(planId).toPlan()
    }

    override suspend fun insertPlanById(plan: Plan) {
        planDao.insertPlanById(plan.toPlanLocal())
    }

    override suspend fun insertClasificacion(clasificaciones: List<Clasificacion>) {
        clasificacionDao.insertClasificacion(clasificaciones.map{ it.toClasificacionLocal()})
    }

    override fun getAllClasificacion(): Flow<List<Clasificacion>> {
        return clasificacionDao.getAllClasificacion().map { clasificaciones ->  clasificaciones.map { it.toClasificacion() } }
    }

    override suspend fun getClasificacionById(clasificacionId: Int): Clasificacion {
        return clasificacionDao.getClasificacionById(clasificacionId).toClasificacion()
    }
}