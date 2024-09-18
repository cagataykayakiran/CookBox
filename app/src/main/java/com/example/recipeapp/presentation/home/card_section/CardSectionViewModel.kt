package com.example.recipeapp.presentation.home.card_section

import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.use_cases.GetPopularRecipes
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.recipeapp.presentation.home.card_section.CardContract.UiAction
import com.example.recipeapp.presentation.home.card_section.CardContract.UiEffect
import com.example.recipeapp.presentation.home.card_section.CardContract.UiState

@HiltViewModel
class CardSectionViewModel @Inject constructor(
    private val getPopularRecipes: GetPopularRecipes
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    init {
        _uiState.value = UiState()
        getRecipeList()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnRecipeClick -> {
                viewModelScope.launch {
                    emitUiEffect(UiEffect.GoToDetail(uiAction.recipe.id))
                }
            }
        }
    }

    private fun getRecipeList() {
        getPopularRecipes().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    updateUiState { copy(data = result.data ?: emptyList(), isLoading = false) }
                }

                is Resource.Loading -> {
                    updateUiState { copy(isLoading = true) }
                }

                is Resource.Error -> {
                   updateUiState { copy(error = result.message ?: "Error!", isLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }
}

object CardContract {
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