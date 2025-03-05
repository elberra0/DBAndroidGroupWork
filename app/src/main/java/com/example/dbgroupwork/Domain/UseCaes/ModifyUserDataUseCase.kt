package com.example.dbgroupwork.Domain.UseCaes

import com.example.dbgroupwork.Domain.Models.UserData
import com.example.dbgroupwork.Domain.UserRepository

class ModifyUserDataUseCase (private val repository: UserRepository){
    suspend operator fun invoke(email:String,username:String,password:String){
        val userdata = UserData(email,username,password)
        repository.modifyUserData(userdata)
    }
}