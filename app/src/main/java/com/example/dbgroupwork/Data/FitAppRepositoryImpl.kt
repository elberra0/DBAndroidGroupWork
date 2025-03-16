package com.example.dbgroupwork.data

import com.example.dbgroupwork.Domain.FitAppRepository
import com.example.dbgroupwork.Domain.Models.Clasificacion
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
        if(expired){
            updateFitAppDataFromRest()
        }

        val localPlanes = fitAppDatabaseDatasource.getAllPlan().first()

        return localPlanes
    }

    private suspend fun updateFitAppDataFromRest() {
        runCatching {
            val remotePlanes = remoteDataSource.getPlanes()
            val clasificaciones = remoteDataSource.getClasificaciones()
            fitAppDatabaseDatasource.insertClasificacion(clasificaciones)
            fitAppDatabaseDatasource.insertPlan(remotePlanes)
        }
    }

    override suspend fun getPlanById(planId: Int): Plan {
        val expired = true
        if(expired){
            updateFitAppDataFromRest()
        }
        return fitAppDatabaseDatasource.getPlanById(planId)
    }

    override suspend fun getAllClasificacion(): List<Clasificacion> {
        val expired = true
        if(expired){
            updateFitAppDataFromRest()
        }

        val localClasificaciones = fitAppDatabaseDatasource.getAllClasificacion().first()
        //val lastRefreshDate = localPrefDataSource.getRefreshTimestamp().first()
        //val expired = lastRefreshDate.isBefore(Instant.now().minus(Duration.ofDays(4)))
        return localClasificaciones
    }

    override suspend fun getClasificacionById(clasificacionId: Int): Clasificacion {
        return fitAppDatabaseDatasource.getClasificacionById(clasificacionId)
    }
}