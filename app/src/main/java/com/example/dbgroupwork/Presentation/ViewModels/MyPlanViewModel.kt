package com.example.dbgroupwork.Presentation.ViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.Domain.UseCaes.GetPlanByIdUseCase
import android.content.Context
import com.example.dbgroupwork.data.AppDataSharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyPlanViewModel (private val getPlanByIdUseCase: GetPlanByIdUseCase, private val context: Context):ViewModel() {
    private val _appDataSharedPreferences = AppDataSharedPreferences()
    var myPlan: Plan? = null

   suspend fun getMyPlan() {
      val planId = _appDataSharedPreferences.getPlan(context)
       myPlan = getPlanByIdUseCase.getPlanById(planId)
    }
}