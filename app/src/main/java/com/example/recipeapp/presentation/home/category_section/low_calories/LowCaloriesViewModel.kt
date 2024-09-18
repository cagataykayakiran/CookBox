package com.example.recipeapp.presentation.home.category_section.low_calories

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.use_cases.GetRecipesByLowCalories
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.presentation.home.category_section.low_calories.LowCaloriesContract.UiAction
import com.example.recipeapp.presentation.home.category_section.low_calories.LowCaloriesContract.UiEffect
import com.example.recipeapp.presentation.home.category_section.low_calories.LowCaloriesContract.UiState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LowCaloriesViewModel @Inject constructor(
    private val recipesByLowCalories: GetRecipesByLowCalories,
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getLowCalories()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnRecipeClick -> {
                viewModelScope.launch {
                    Log.d("LowCaloriesViewModel", "Recipe clicked: ${uiAction.recipe.id}")
                    emitUiEffect(UiEffect.GoToDetail(uiAction.recipe.id))
                }
            }
        }
    }

    private fun getLowCalories() {
        recipesByLowCalories()
            .flowOn(Dispatchers.IO)  // Veri çekme işlemini arka planda yapar
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        updateUiState {
                            copy(
                                data = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        updateUiState {
                            copy(isLoading = true)
                        }
                    }

                    is Resource.Error -> {
                        updateUiState {
                            copy(
                                error = result.message ?: "Error!",
                                isLoading = false
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)  // UI güncellemelerini ana iş parçacığında yapar

    }
}

object LowCaloriesContract {
    data class UiState(
        val isLoading: Boolean = false,
        val data: List<Recipe> = emptyList(),
        val error: String = ""
    )

    sealed class UiAction {
        data class OnRecipeClick(val recipe: Recipe) : UiAction()
    }

    sealed class UiEffect {
        data class GoToDetail(val recipeId: Int) : UiEffect()
    }
}