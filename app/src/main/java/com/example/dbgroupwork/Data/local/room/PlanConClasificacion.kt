package com.example.dbgroupwork.Data.local.room

import androidx.room.Embedded
import androidx.room.Relation

data class PlanConClasificacion(
    @Embedded val plan: PlanLocal,
    @Relation(
        parentColumn = "clasificacionid",
        entityColumn = "id"
    )
    val clasificacion: ClasificacionLocal
)