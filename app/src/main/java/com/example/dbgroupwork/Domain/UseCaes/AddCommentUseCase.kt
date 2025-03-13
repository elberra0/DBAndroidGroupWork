package com.example.dbgroupwork.Domain.UseCaes

import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.Domain.UserRepository

class AddCommentUseCase(private val userRepository: UserRepository) {
    suspend fun addComment(comment: Comment) {
        userRepository.addComment(comment = comment)
    }
}