package com.example.dbgroupwork.Presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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

    fun toMain(context: Context, navController: NavController) {
        navController.navigate("main")
    }
}