package com.example.dbgroupwork.Domain.Models

data class DiaSemanaBase(
    val tipo: String,
    val calentamiento: String?,
    val ejercicios: List<Ejercicio>,
    val enfriamiento: String?
)