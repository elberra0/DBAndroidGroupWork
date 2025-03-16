package com.example.dbgroupwork.Data

import androidx.room.TypeConverter
import com.example.dbgroupwork.Domain.Models.Clasificacion
import com.example.dbgroupwork.data.local.room.PlanLocal
import com.example.dbgroupwork.Domain.Models.Plan

import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.data.local.firebase.CommentsData
import com.example.dbgroupwork.data.local.room.ClasificacionLocal
import com.example.dbgroupwork.data.remote.DayPlan
import com.example.dbgroupwork.data.remote.fromEjerciciosMap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun CommentsData.toDomain(): Comment {
    return Comment(
        id = id, comment = comment, author = author)
}

fun Plan.toPlanLocal():PlanLocal {
    return  PlanLocal(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion,
        ejercicios =  fromEjerciciosMap(ejercicios)
    )
}

fun PlanLocal.toPlan():Plan {
    return  Plan(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion,
        ejercicios = toEjerciciosMap(ejercicios)
    )
}

@TypeConverter
fun toEjerciciosMap(value: String): Map<String, DayPlan> {
    val type = object : TypeToken<Map<String, DayPlan>>() {}.type
    val gson = Gson()
    return gson.fromJson(value, type)
}


fun Clasificacion.toClasificacionLocal():ClasificacionLocal {
    return  ClasificacionLocal(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        puntajemaximo = puntajemaximo,
        puntajeminimo = puntajeminimo
    )
}

fun ClasificacionLocal.toClasificacion():Clasificacion {
    return  Clasificacion(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        puntajemaximo = puntajemaximo,
        puntajeminimo = puntajeminimo
    )
}