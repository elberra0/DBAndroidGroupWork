package com.example.dbgroupwork.Domain.Models

import kotlinx.serialization.Serializable


@Serializable
data class Comment (
    val id:Long,
    val comment: String,
    val author: String)


