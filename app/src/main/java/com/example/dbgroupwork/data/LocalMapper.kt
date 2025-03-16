package com.example.dbgroupwork.Data

import com.example.dbgroupwork.Domain.Models.Clasificacion
import com.example.dbgroupwork.data.local.room.PlanLocal
import com.example.dbgroupwork.Domain.Models.Plan

import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.data.local.firebase.CommentsData
import com.example.dbgroupwork.data.local.room.ClasificacionLocal

fun CommentsData.toDomain(): Comment {
    return Comment(
        id = id, comment = comment, author = author)
}

fun Plan.toPlanLocal():PlanLocal {
    return  PlanLocal(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion,
        ejercicios = ejercicios
    )
}

fun PlanLocal.toPlan():Plan {
    return  Plan(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion,
        ejercicios = ejercicios
    )
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