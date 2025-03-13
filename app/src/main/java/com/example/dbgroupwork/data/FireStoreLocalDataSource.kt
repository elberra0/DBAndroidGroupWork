package com.example.dbgroupwork.data

import com.example.dbgroupwork.Domain.Models.Comment
import kotlinx.coroutines.flow.Flow

interface FireStoreLocalDataSource {
    suspend fun getComments(): Flow<List<Comment>>
    suspend fun addComment(comment:Comment)
}