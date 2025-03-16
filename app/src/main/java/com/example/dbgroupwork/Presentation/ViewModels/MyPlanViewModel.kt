package com.example.dbgroupwork.Presentation.ViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbgroupwork.Domain.Models.Plan
import com.example.dbgroupwork.Domain.UseCaes.GetPlanByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyPlanViewModel (private val getPlanByIdUseCase: GetPlanByIdUseCase):ViewModel() {
    var myPlan: Plan? = null

   suspend fun getMyPlan() {
       myPlan = getPlanByIdUseCase.getPlanById(1)
    }
}