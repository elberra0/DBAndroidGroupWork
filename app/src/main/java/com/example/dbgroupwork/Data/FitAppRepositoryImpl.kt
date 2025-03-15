package com.example.dbgroupwork.data

import com.example.dbgroupwork.Domain.FitAppRepository
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.data.local.FitAppDatabaseDatasource
import com.example.dbgroupwork.data.remote.FitAppRemoteDataSource
import kotlinx.coroutines.flow.first
import java.time.Duration
import java.time.Instant

class FitAppRepositoryImpl(
    private val remoteDataSource: FitAppRemoteDataSource,
    private val fitAppDatabaseDatasource:  FitAppDatabaseDatasource) :FitAppRepository {

    override suspend fun getAllPlan(): List<Plan> {
        val expired = true
        updatePlanes()
        val localPlanes = fitAppDatabaseDatasource.getAllPlan().first()
        //val lastRefreshDate = localPrefDataSource.getRefreshTimestamp().first()
        //val expired = lastRefreshDate.isBefore(Instant.now().minus(Duration.ofDays(4)))
        return localPlanes
    }

    private suspend fun updatePlanes() {
        runCatching {

            val remotePlanes = remoteDataSource.getPlanes()
            val Plan = remoteDataSource.getPlanById(2)
            fitAppDatabaseDatasource.insertPlan(remotePlanes)
        }
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
        return fitAppDatabaseDatasource.getPlanById(planId)
    }
}