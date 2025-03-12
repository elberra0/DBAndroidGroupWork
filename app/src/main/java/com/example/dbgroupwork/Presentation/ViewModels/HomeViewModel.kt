package com.example.dbgroupwork.Presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.dbgroupwork.Domain.UseCaes.CheckUserToLoginUseCase
import com.example.dbgroupwork.Domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val checkUserDataToLoginUseCase: CheckUserToLoginUseCase,
                     private val userRepository: UserRepository
): ViewModel() {
    private val _emailOrUsername = MutableStateFlow("")

    val emailOrUsername: StateFlow<String> = _emailOrUsername

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


    fun loginUserData(context: Context,navController:NavController) {
        if(emailOrUsername.value.length != 0 && password.value.length != 0){
                viewModelScope.launch {
                    if(checkUserDataToLoginUseCase(_emailOrUsername.value, _password.value)){
                        navController.navigate("main")
                    }
                }
        }else{
            Toast.makeText(context, "Credentials missing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserData() {
        viewModelScope.launch {
            userRepository.getUserData().collect { userData ->
                _emailOrUsername.value = userData.username
                _password.value = userData.password
            }
        }
    }

    fun onEmailOrUsernameChanged(newEmailOrUsername: String) {
        _emailOrUsername.value = newEmailOrUsername
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }
}