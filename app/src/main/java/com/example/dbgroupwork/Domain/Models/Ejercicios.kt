package com.example.dbgroupwork.Domain.Models

data class Ejercicios(
        val lunes: DiaSemanaBase,
        val martes: DiaSemanaBase,
        val miercoles: DiaSemanaBase,
        val jueves: DiaSemanaBase,
        val viernes: DiaSemanaBase,
        val sabado: DiaSemanaBase,
        val domingo: DiaSemanaBase
    )