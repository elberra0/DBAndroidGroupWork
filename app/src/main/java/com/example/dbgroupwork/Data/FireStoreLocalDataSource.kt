package com.example.dbgroupwork.Data

import com.example.dbgroupwork.Domain.Models.Comment
import kotlinx.coroutines.flow.Flow

interface FireStoreLocalDataSource {
    fun getComments(monumentId:Long): Flow<List<Comment>>
    suspend fun addComment(comment:Comment)
}