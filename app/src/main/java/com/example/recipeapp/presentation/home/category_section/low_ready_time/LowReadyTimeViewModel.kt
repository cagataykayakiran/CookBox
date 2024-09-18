package com.example.recipeapp.presentation.home.category_section.low_ready_time

import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.use_cases.GetRecipesByLowReadyTime
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.presentation.home.category_section.low_ready_time.LowReadyTimeContract.UiAction
import com.example.recipeapp.presentation.home.category_section.low_ready_time.LowReadyTimeContract.UiEffect
import com.example.recipeapp.presentation.home.category_section.low_ready_time.LowReadyTimeContract.UiState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LowReadyTimeViewModel @Inject constructor(
    private val recipesByLowReadyTime: GetRecipesByLowReadyTime
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnRecipeClick -> {
                viewModelScope.launch {
                    emitUiEffect(UiEffect.GoToDetail(uiAction.recipe.id))
                }
            }
        }
    }

    init {
        getLowReadyTime()
    }

    private fun getLowReadyTime() {
        recipesByLowReadyTime()
            .flowOn(Dispatchers.IO)
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
                            copy(
                                isLoading = true
                            )
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
            }.launchIn(viewModelScope)
    }

}

object LowReadyTimeContract {
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