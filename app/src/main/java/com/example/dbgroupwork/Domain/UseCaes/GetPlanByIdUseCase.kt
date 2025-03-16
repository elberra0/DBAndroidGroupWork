package com.example.dbgroupwork.Domain.UseCaes

import com.example.dbgroupwork.Domain.FitAppRepository
import com.example.dbgroupwork.Domain.Models.Plan

class GetPlanByIdUseCase(private val repository: FitAppRepository) {
    suspend fun getPlanById(planId: Int): Plan {
        return repository.getPlanById(planId)
    }
}