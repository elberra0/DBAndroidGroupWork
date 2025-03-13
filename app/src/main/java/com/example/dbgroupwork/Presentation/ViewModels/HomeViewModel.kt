package com.example.dbgroupwork.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.Domain.PlanRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val planRepository: PlanRepository
): ViewModel() {
    fun insert() {
        viewModelScope.launch {
            planRepository.insertPlan(Plan(id = 1, clasificacionid = 1, clasificacion = "hola 1"))
        }
    }
}