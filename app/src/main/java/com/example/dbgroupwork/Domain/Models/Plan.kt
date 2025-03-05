package com.example.dbgroupwork.Domain.Models

data class Plan (
    val id: Int,
    val clasificacionId: Int,
    val ejercicios: Ejercicios,
    val consejos: List<String>
)
