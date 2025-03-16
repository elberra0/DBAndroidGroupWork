package com.example.dbgroupwork.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ClasificacionRoom")
data class ClasificacionLocal(
    @PrimaryKey val id: Int,
    val puntajeminimo: Int,
    val puntajemaximo: Int,
    val descripcion: String,
    val nombre: String
)