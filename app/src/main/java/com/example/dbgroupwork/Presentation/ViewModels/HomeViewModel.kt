package com.example.dbgroupwork.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbgroupwork.Domain.FitAppRepository
import com.example.dbgroupwork.Domain.Models.Plan
import kotlinx.coroutines.launch

class HomeViewModel(private val fitAppRepository: FitAppRepository
): ViewModel() {
    fun insert() {
        viewModelScope.launch {
            fitAppRepository.getAllPlan()
        }
    }
}