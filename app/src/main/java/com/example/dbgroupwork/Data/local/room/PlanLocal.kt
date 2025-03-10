package com.example.dbgroupwork.Data.local.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


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
    val clasificacion: String
)