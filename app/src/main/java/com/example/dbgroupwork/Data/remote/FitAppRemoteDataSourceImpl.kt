package com.example.dbgroupwork.data.remote

import androidx.room.TypeConverter
import com.example.dbgroupwork.Domain.Models.Plan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
        clasificacion = clasificacion,
        ejercicios = fromEjerciciosMap(ejercicios)
    )
}

fun WorkoutPlan.toDomain(): Plan {
    return Plan(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion,
        ejercicios = fromEjerciciosMap(ejercicios)
    )
}

@TypeConverter
fun fromEjerciciosMap(value: Map<String, DayPlan>): String {
    val gson = Gson()
    return gson.toJson(value)
}

@TypeConverter
fun toEjerciciosMap(value: String): Map<String, DayPlan> {
    val type = object : TypeToken<Map<String, DayPlan>>() {}.type
    val gson = Gson()
    return gson.fromJson(value, type)
}