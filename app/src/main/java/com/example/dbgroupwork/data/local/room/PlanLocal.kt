package com.example.dbgroupwork.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PlanRoom")
data class PlanLocal(
    @PrimaryKey val id: Int,
    val clasificacionid: Int,
    val clasificacion: String
)