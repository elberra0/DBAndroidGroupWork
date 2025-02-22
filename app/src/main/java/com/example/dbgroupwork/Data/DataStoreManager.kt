package com.example.dbgroupwork.Data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("user_prefs")

class DataStoreManager(private val context: Context) {

    companion object{
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val PASSWORD_KEY = stringPreferencesKey("password")
    }

    suspend fun saveUserData(userData: UserData){
        context.datastore.edit { preferences->
            preferences[EMAIL_KEY] = userData.email
            preferences[USERNAME_KEY] = userData.username
            preferences[PASSWORD_KEY] = userData.password
        }
        Log.d("DataStore", "Datos guardados: $userData")
    }

    val userData:Flow<UserData> = context.datastore.data.map {
        preferences->
        UserData(
            email = preferences[EMAIL_KEY] ?: "",
            username = preferences[USERNAME_KEY] ?: "",
            password = preferences[PASSWORD_KEY] ?: "",
        )
    }
}