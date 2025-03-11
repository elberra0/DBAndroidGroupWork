package com.example.dbgroupwork.Domain
//import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUserData(userData: UserData): Boolean
    suspend fun modifyUserData(userData: UserData)
    suspend fun checkUserToLogin(userData: UserData): Boolean
    fun getUserData(): Flow<UserData>
    suspend fun getCurrentUserId(): String?
    suspend fun getAllUserIds(): Set<String>
    suspend fun setCurrentUser(userId: String)
   // suspend fun addComment(comment: Comment)
   // fun getComments(commentId:Long):Flow<List<Comment>>
}