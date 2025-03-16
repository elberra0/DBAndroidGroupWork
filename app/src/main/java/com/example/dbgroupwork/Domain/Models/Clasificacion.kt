package com.example.dbgroupwork.Domain.Models

data class Clasificacion(
   val id: Int,
    val puntajeminimo: Int,
    val puntajemaximo: Int,
    val descripcion: String,
    val nombre: String
)