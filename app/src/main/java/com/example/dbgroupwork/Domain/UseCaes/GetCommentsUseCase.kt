package com.example.dbgroupwork.Domain.UseCaes

import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.Domain.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase (private val userRepository: UserRepository){
    suspend fun getComments():Flow<List<Comment>>{
        return userRepository.getComments()
    }
}