package com.example.dbgroupwork.Domain.UseCaes

import com.example.dbgroupwork.Domain.Models.UserData
import com.example.dbgroupwork.Domain.UserRepository

class CheckUserToLoginUseCase (private val repository: UserRepository){
    suspend operator fun invoke(emailOrUsername:String,password:String):Boolean{
        val userdata = UserData(emailOrUsername,emailOrUsername,password)
        return repository.checkUserToLogin(userdata)
    }
}