package com.example.dbgroupwork.Data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID

private val Context.datastore by preferencesDataStore("user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val USER_IDS_KEY = stringSetPreferencesKey("user_ids")
        private fun emailKey(userId: String) = stringPreferencesKey("${userId}_email")
        private fun usernameKey(userId: String) = stringPreferencesKey("${userId}_username")
        private fun passwordKey(userId: String) = stringPreferencesKey("${userId}_password")
    }

    suspend fun saveUserIfNotRegistered(userData:UserData):String?{
        if(!existsUserDatastore(userData)){
            val userId = UUID.randomUUID().toString()
            saveUserData(userId,userData)
            return userId
        }else{
            Toast.makeText(context, "User con esos credenciales ya registrado", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    private suspend fun saveUserData(userId:String, userData: UserData){
        context.datastore.edit { preferences->

            preferences[emailKey(userId)] = userData.email
            preferences[usernameKey(userId)] = userData.username
            preferences[passwordKey(userId)] = userData.password

            val currentUserIds = preferences[USER_IDS_KEY] ?: emptySet()
            preferences[USER_IDS_KEY] = currentUserIds + userId
        }
        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
        Log.d("DataStore", "Datos guardados para usuario $userId: $userData")
    }

    suspend fun modifyUserData(userId:String, userData: UserData) {
        context.datastore.edit { preferences ->
            if (userData.email.isNotEmpty()) {
                preferences[emailKey(userId)] = userData.email
                Toast.makeText(context, "Email modified", Toast.LENGTH_SHORT).show()
            }

            if (userData.username.isNotEmpty()) {
                preferences[usernameKey(userId)] = userData.username
                Toast.makeText(context, "Username modified", Toast.LENGTH_SHORT).show()
            }

            if (userData.password.isNotEmpty()) {
                preferences[passwordKey(userId)] = userData.password
                Toast.makeText(context, "Password modified", Toast.LENGTH_SHORT).show()
            }
        }
        Log.d("DataStore", "Datos modificados para usuario $userId: $userData")
    }

    suspend fun checkUserToLogin(userData: UserData): String? {
        val userIds = context.datastore.data.first()[USER_IDS_KEY] ?: emptySet()

        for (userId in userIds) {
            val storedUserData = context.datastore.data.first()
            if ((storedUserData[emailKey(userId)] == userData.email ||
                        storedUserData[usernameKey(userId)] == userData.username) &&
                storedUserData[passwordKey(userId)] == userData.password) {
                Toast.makeText(context, "Loggin in", Toast.LENGTH_SHORT).show()
                return userId
            }
        }
        Toast.makeText(context, "Invalid credentials or need to sign up", Toast.LENGTH_SHORT).show()
        return null
    }

    private suspend fun existsUserDatastore(userData: UserData): Boolean {
        val userIds = context.datastore.data.first()[USER_IDS_KEY] ?: emptySet()
        return userIds.any { userId ->
            val preferences = context.datastore.data.first()
            preferences[emailKey(userId)] == userData.email ||
                    preferences[usernameKey(userId)] == userData.username
        }
    }

    fun userData(userId: String): Flow<UserData> = context.datastore.data.map { preferences ->
        UserData(
            email = preferences[emailKey(userId)] ?: "",
            username = preferences[usernameKey(userId)] ?: "",
            password = preferences[passwordKey(userId)] ?: ""
        )
    }

    suspend fun getAllUserIds(): Set<String> {
        return context.datastore.data.first()[USER_IDS_KEY] ?: emptySet()
    }
}