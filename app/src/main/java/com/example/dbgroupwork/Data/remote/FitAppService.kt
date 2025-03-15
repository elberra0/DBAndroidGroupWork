package com.example.dbgroupwork.data.remote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
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
    @SerialName("clasificacion") val clasificacion: String,
    @SerialName("ejercicios") val ejercicios: Map<String, DayPlan>
)

@Serializable
data class WorkoutPlan(
    @SerialName("id") val id: Int,
    @SerialName("clasificacionid") val clasificacionid: Int,
    @SerialName("clasificacion") val clasificacion: String,
    @SerialName("ejercicios") val ejercicios: Map<String, DayPlan>
)

@Serializable
data class DayPlan(
    @SerialName("tipo")val tipo: String,
    @SerialName("calentamiento") val calentamiento: String,
    @SerialName("ejercicios") val ejercicios: List<Exercise>
)

@Serializable
data class Exercise(
    @SerialName("nombre")val nombre: String,
    @SerialName("series")val series: Int,
    @SerialName("repeticiones")val repeticiones: String
)