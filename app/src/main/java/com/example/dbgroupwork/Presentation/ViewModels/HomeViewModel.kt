package com.example.dbgroupwork.Presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.Domain.PlanRepository
import com.example.dbgroupwork.Domain.UseCaes.CheckUserToLoginUseCase
import com.example.dbgroupwork.Domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val planRepository: PlanRepository
): ViewModel() {

    fun insert() {
        viewModelScope.launch {
            planRepository.insertPlan(Plan(id = 1, clasificacionid = 1, clasificacion = "hola 1"))

        }
    }
}