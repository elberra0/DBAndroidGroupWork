package com.example.dbgroupwork.data.remote

import com.example.dbgroupwork.Domain.Models.Clasificacion
import com.example.dbgroupwork.Domain.Models.Plan

interface FitAppRemoteDataSource {
    suspend fun getPlanes(): List<Plan>

    suspend fun getPlanById(id: Int): Plan

    suspend fun getClasificaciones(): List<Clasificacion>
}