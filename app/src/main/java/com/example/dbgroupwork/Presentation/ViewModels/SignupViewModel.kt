package com.example.dbgroupwork.Presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.dbgroupwork.Domain.UseCaes.SaveUserDataUseCase
import com.example.dbgroupwork.Domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SignupViewModel (private val saveUserDataUseCase: SaveUserDataUseCase,
    private val userRepository: UserRepository):ViewModel(){

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm
    
    fun saveUserData(context: Context) {
        if(password.value == passwordConfirm.value){
            if(email.value.length != 0 && username.value.length != 0){
                viewModelScope.launch {
                    saveUserDataUseCase(_email.value, _username.value, _password.value)
                }
            }else{
                Toast.makeText(context, "Credentials missing", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onUsernameChanged(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onPasswordConfirmChanged(newPassword: String) {
        _passwordConfirm.value = newPassword
    }
}