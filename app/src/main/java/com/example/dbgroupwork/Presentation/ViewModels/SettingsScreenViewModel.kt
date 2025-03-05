package com.example.dbgroupwork.Presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbgroupwork.Domain.UseCaes.ModifyUserDataUseCase
import com.example.dbgroupwork.Domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsScreenViewModel(private val modifyUserDataUseCase: ModifyUserDataUseCase, private val userRepository: UserRepository): ViewModel()  {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm

    fun modifyUserData(context: Context) {
        var modified = false

        if(email.value.isNotEmpty()){
            viewModelScope.launch {
                modifyUserDataUseCase(_email.value, _username.value, _password.value)
            }
            Toast.makeText(context, "Email modified", Toast.LENGTH_SHORT).show()
            modified = true
        }

        if(username.value.isNotEmpty()){
            viewModelScope.launch {
                modifyUserDataUseCase(_email.value, _username.value, _password.value)
            }
            Toast.makeText(context, "Username modified", Toast.LENGTH_SHORT).show()
            modified = true
        }

        if(password.value.isNotEmpty()){
            if(password.value == passwordConfirm.value){
                viewModelScope.launch {
                    modifyUserDataUseCase(_email.value, _username.value, _password.value)
                }
                modified = true
            }else{
                Toast.makeText(context, "New password does not match confirmation", Toast.LENGTH_SHORT).show()
            }
        }

        if(modified){
            Toast.makeText(context, "Credenciales actualizados", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Credenciales no actualizados ya que no hay nada que cambiar", Toast.LENGTH_SHORT).show()
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