package com.example.dbgroupwork.Data.Repository

import com.example.dbgroupwork.Data.DataStoreManager
import com.example.dbgroupwork.Domain.UserRepository
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl (private val dataStoreManager: DataStoreManager) : UserRepository {
    override suspend fun saveUserData(userData: UserData) {
        dataStoreManager.saveUserIfNotRegistered(userData)
    }

    override fun getUserData(): Flow<UserData> {
        return dataStoreManager.userData
    }
}