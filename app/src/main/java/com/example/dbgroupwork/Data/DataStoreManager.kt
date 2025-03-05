package com.example.dbgroupwork.Data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.dbgroupwork.Domain.Models.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("user_prefs")

class DataStoreManager(private val context: Context) {

    companion object{
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val PASSWORD_KEY = stringPreferencesKey("password")
    }

    suspend fun saveUserIfNotRegistered(userData:UserData){
        if(!existsUserDatastore(userData)){
            saveUserData(userData)
        }else{
            Toast.makeText(context, "User con esos credenciales ya registrado", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveUserData(userData: UserData){
        context.datastore.edit { preferences->
            preferences[EMAIL_KEY] = userData.email
            preferences[USERNAME_KEY] = userData.username
            preferences[PASSWORD_KEY] = userData.password
        }
        Log.d("DataStore", "Datos guardados: $userData")
    }

    suspend fun checkUserToLogin(userData: UserData): Boolean {
        val storedUserData = context.datastore.data.first()
        val isLoginSuccessful =  (storedUserData[EMAIL_KEY] == userData.email ||
                storedUserData[USERNAME_KEY] == userData.username) &&
                storedUserData[PASSWORD_KEY] == userData.password

        if(isLoginSuccessful){
            Toast.makeText(context, "Logging In", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Wrong credentials or User does not exist", Toast.LENGTH_SHORT).show()
        }
        return isLoginSuccessful
    }

    suspend fun existsUserDatastore(userData:UserData):Boolean{
        return context.datastore.data.map { preferences->
            preferences[EMAIL_KEY] == userData.email ||
            preferences[USERNAME_KEY] == userData.username
        }.firstOrNull() ?: false
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