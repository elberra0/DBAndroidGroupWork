package com.example.dbgroupwork.Data.Repository

import com.example.dbgroupwork.Data.DataStoreManager
import com.example.dbgroupwork.Data.FireStoreLocalDataSource
import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.Domain.UserRepository
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val fireStoreLocalDataSource: FireStoreLocalDataSource, private val dataStoreManager: DataStoreManager) : UserRepository {

    private var currentUserId: String? = null

    override suspend fun saveUserData(userData: UserData): Boolean {
        val userId = dataStoreManager.saveUserIfNotRegistered(userData)
        currentUserId = userId
        return userId != null
    }

    override suspend fun modifyUserData(userData: UserData) {
        currentUserId?.let { userId ->
            dataStoreManager.modifyUserData(userId, userData)
        } ?: throw IllegalStateException("No user to modify found")
    }

    override suspend fun checkUserToLogin(userData: UserData): Boolean {
        val userId = dataStoreManager.checkUserToLogin(userData)
        currentUserId = userId
        return userId != null
    }

    override fun getUserData(): Flow<UserData> {
        return currentUserId?.let { userId ->
            dataStoreManager.userData(userId)
        } ?: throw IllegalStateException("No user to get found")
    }

    override suspend fun getCurrentUserId(): String? {
        return currentUserId
    }

    override suspend fun getAllUserIds(): Set<String> {
        return dataStoreManager.getAllUserIds()
    }

    override suspend fun setCurrentUser(userId: String) {
        currentUserId = userId
    }

    override suspend fun addComment(comment: Comment) {
        TODO("Not yet implemented")
    }

    override fun getComments(monumentId: Long): Flow<List<Comment>> {
        TODO("Not yet implemented")
    }
}