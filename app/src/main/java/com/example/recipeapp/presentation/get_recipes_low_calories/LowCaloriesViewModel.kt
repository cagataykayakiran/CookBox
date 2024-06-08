package com.example.recipeapp.presentation.get_recipes_low_calories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.util.Resource
import com.example.recipeapp.domain.use_cases.GetRecipesByLowCalories
import com.example.recipeapp.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LowCaloriesViewModel @Inject constructor(
    private val getRecipesByLowCalories: GetRecipesByLowCalories,
): ViewModel() {

    private val _lowCaloriesState = MutableStateFlow(MainState.LowCaloriesState())
    val lowCaloriesState = _lowCaloriesState.asStateFlow()

    init {
        getLowCalories()
    }

    private fun getLowCalories() {
        getRecipesByLowCalories().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _lowCaloriesState.value = _lowCaloriesState.value.copy(
                        data = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _lowCaloriesState.value = _lowCaloriesState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _lowCaloriesState.value = _lowCaloriesState.value.copy(
                        error = result.message ?: "Error!",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}