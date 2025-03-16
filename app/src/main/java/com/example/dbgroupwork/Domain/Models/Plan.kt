package com.example.dbgroupwork.Domain.Models

import com.example.dbgroupwork.data.remote.DayPlan

data class Plan (
    val id: Int,
    val clasificacionid: Int,
    val clasificacion: String,
    val ejercicios: Map<String, DayPlan>
)
