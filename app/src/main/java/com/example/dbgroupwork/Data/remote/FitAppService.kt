package com.example.dbgroupwork.data.remote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FitAppService {
    @GET("GetPlanes")
    suspend fun getPlanes(): List<PlanRemote>

    @GET("GetPlanById")
    suspend fun getPlanById(@Query("id") planId: Int): PlanRemote
}

@Serializable
data class PlanRemote(
    @SerialName("id") val id: Int,
    @SerialName("clasificacionid") val clasificacionid: Int,
    @SerialName("clasificacion") val clasificacion: String
//    @SerialName("ejercicios")
//    val ejercicios: Ejercicios,
)
