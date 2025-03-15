package com.example.dbgroupwork.data.remote

import com.example.dbgroupwork.Domain.Models.Plan

class FitAppRemoteDataSourceImpl(private val fitAppService: FitAppService): FitAppRemoteDataSource
{
    override suspend fun getPlanes(): List<Plan> {
        return fitAppService.getPlanes().map { planes -> planes.toDomain() }
    }

    override suspend fun getPlanById(id: Int): Plan {
        return  fitAppService.getPlanById(id).toDomain()
    }
}

fun PlanRemote.toDomain(): Plan {
    return Plan(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion
    )
}