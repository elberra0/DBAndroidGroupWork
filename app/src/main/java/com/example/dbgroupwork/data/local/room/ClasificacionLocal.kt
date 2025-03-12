package com.example.dbgroupwork.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ClasificacionRoom")
data class ClasificacionLocal(
    @PrimaryKey val id: Int,
    val puntajeMinimo: Int,
    val puntajeMaximo: Int,
    val descripcion: String,
    val nombre: String
)