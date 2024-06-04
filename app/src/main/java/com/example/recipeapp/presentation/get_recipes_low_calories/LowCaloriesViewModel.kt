package com.example.recipeapp.presentation.get_recipes_low_calories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.Resource
import com.example.recipeapp.domain.use_cases.GetRecipesByLowCalories
import com.example.recipeapp.domain.use_cases.GetTypeByRecipes
import com.example.recipeapp.presentation.get_category_recipes.RecipeTypeState
import com.example.recipeapp.presentation.get_category_recipes.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LowCaloriesViewModel @Inject constructor(
    private val getRecipesByLowCalories: GetRecipesByLowCalories
): ViewModel() {

    private val _lowCaloriesState = MutableStateFlow(LowCaloriesState())
    val lowCaloriesState = _lowCaloriesState.asStateFlow()

    init {
        getLowCalories()
    }
    private fun getLowCalories() {
        getRecipesByLowCalories().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _lowCaloriesState.update {
                        it.copy(
                            recipe = result.data ?: emptyList(),
                        )
                    }
                }
                is Resource.Loading -> {
                    _lowCaloriesState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Error -> {
                    _lowCaloriesState.update {
                        it.copy(
                            error = result.message ?: "Error",
                        )
                    }
                }

                else -> {
                    println("Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}