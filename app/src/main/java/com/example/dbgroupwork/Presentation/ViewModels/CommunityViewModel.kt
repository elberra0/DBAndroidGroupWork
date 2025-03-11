package com.example.dbgroupwork.Presentation.ViewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
//import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.Domain.UseCaes.AddCommentUseCase
import com.example.dbgroupwork.Domain.UseCaes.GetCommentsUseCase
import com.example.dbgroupwork.Presentation.Mapper.CommentUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommunityViewModel (savedState:SavedStateHandle,
   // private val getCommentsUseCase: GetCommentsUseCase,
  //  private val addCommentUseCase: AddCommentUseCase
):ViewModel(){

    private val _state: MutableStateFlow<CommentsState> = MutableStateFlow(CommentsState.Loading)
    val state: StateFlow<CommentsState> = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), CommentsState.Loading)
  //  val id = savedState.toRoute<Comment>().id

    fun onAddReview(comment: String, author: String) {
        viewModelScope.launch {
           // val commentUser = Comment(id = 1, comment = comment, author = author)
           // addCommentUseCase.addComment(commentUser)
            }
        }
}
sealed class CommentsState {
    data object Loading : CommentsState()
    data class Success(val reviews: List<CommentUI>) : CommentsState()
    data class Error(val message: String) :CommentsState()
}