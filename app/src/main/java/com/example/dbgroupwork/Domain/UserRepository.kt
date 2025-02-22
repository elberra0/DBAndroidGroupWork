package com.example.dbgroupwork.Domain
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUserData(userData: UserData)
    fun getUserData():Flow<UserData>
}