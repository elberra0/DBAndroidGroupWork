package com.example.dbgroupwork.Presentation.ViewModels
import androidx.lifecycle.ViewModel
import com.example.dbgroupwork.Domain.UseCaes.GetPlanByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DoTestViewModel (private val getPlanByIdUseCase: GetPlanByIdUseCase):ViewModel() {
    private val _age = MutableStateFlow(0)
    val age: StateFlow<Int> = _age

    private val _genderSelected = MutableStateFlow("Male")
    val genderSelected: StateFlow<String> = _genderSelected

    private val _height = MutableStateFlow(0)
    val height: StateFlow<Int> = _height

    private val _weight = MutableStateFlow(0)
    val weight: StateFlow<Int> = _weight

    private val _goalSelected = MutableStateFlow("Gain muscle mass")
    val goalSelected: StateFlow<String> = _goalSelected

    fun onAgeChange(age: String) {
        _age.value = age.toIntOrNull() ?: 0
    }

    fun onGenderChange(gender: String) {
        _genderSelected.value = gender
    }

    fun onweightChange(weight: String) {
        _weight.value = weight.toIntOrNull() ?: 0
    }

    fun onheightChange(height: String) {
        _height.value = height.toIntOrNull() ?: 0
    }

    fun onGoalChange(goal: String) {
        _goalSelected.value = goal
    }

    fun assignPlan() {
        var puntosPuntos = 0

        puntosPuntos += when (age.value) {
            in 18..30 -> 3
            in 31..50 -> 2
            else -> 1
        }

        puntosPuntos += when (genderSelected.value) {
            in "Male" -> 2
            else -> 1
        }

        puntosPuntos += when (weight.value) {
            in 0..59 -> 2
            in 60..80 -> 1
            else -> 3
        }

        puntosPuntos += when (height.value) {
            in 0..159 -> 1
            in 160..180 -> 2
            else -> 3
        }

        puntosPuntos +=
            when (goalSelected.value) {
                in "Maintain weight" -> 2
                else -> 3
            }

        var planId = 0
        when (puntosPuntos) {
            in 0..7 -> planId = 3
            in 8..11 -> planId = 2
            in 12..99 -> planId = 1
        }
    }
}