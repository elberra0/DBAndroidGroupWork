package com.example.dbgroupwork.data.local

import com.example.dbgroupwork.Domain.Models.Clasificacion
import com.example.dbgroupwork.Domain.Models.Plan
import kotlinx.coroutines.flow.Flow

interface FitAppDatabaseDatasource {
    suspend fun insertPlan(planes: List<Plan>)
    fun getAllPlan(): Flow<List<Plan>>
    suspend fun getPlanById(planId: Int): Plan
    suspend fun insertPlanById(plan: Plan)

    //Clasificaciones
    suspend fun insertClasificacion(clasificaciones: List<Clasificacion>)
    fun getAllClasificacion(): Flow<List<Clasificacion>>
    suspend fun getClasificacionById(clasificacionId: Int): Clasificacion
}