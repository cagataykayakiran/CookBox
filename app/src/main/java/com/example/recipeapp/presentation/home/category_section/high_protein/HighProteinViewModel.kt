package com.example.recipeapp.presentation.home.category_section.high_protein

import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.use_cases.GetRecipesByHighProtein
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.recipeapp.presentation.home.category_section.high_protein.HighProteinContract.UiAction
import com.example.recipeapp.presentation.home.category_section.high_protein.HighProteinContract.UiEffect
import com.example.recipeapp.presentation.home.category_section.high_protein.HighProteinContract.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class HighProteinViewModel @Inject constructor(
    private val highProteinUseCase: GetRecipesByHighProtein
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
        getHighProtein()
    }

    private fun getHighProtein() {
        highProteinUseCase()
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

object HighProteinContract {
    data class UiState(
        val isLoading: Boolean = false,
        val data: List<Recipe> = emptyList(),
        val error: String = ""
    )

    sealed class UiEffect {
        data class GoToDetail(val recipeId: Int) : UiEffect()
    }

    sealed class UiAction {
        data class OnRecipeClick(val recipe: Recipe) : UiAction()
    }
}

