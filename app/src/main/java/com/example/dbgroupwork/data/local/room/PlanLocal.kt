package com.example.dbgroupwork.data.local.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dbgroupwork.Domain.Models.Clasificacion


@Entity(
    tableName = "PlanRoom",
    foreignKeys = [ForeignKey(
        entity = ClasificacionLocal::class,
        parentColumns = ["id"],
        childColumns = ["clasificacionid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PlanLocal(
    @PrimaryKey val id: Int,
    val clasificacionid: Int,
    val clasificacion: String,
    val ejercicios: String
)