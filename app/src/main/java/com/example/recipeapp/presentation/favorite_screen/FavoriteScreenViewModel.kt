package com.example.recipeapp.presentation.favorite_screen

import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.local.entity.toRecipe
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.use_cases.GetLocalRecipes
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenContract.UiAction
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenContract.UiEffect
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val localRecipes: GetLocalRecipes,
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    init {
        loadRecipes()
    }

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.SelectRecipe -> {
                    emitUiEffect(UiEffect.GotoDetail(uiAction.id))
                }
            }
        }
    }

    private fun loadRecipes() {
        updateUiState {
            copy(isLoading = true)
        }
        localRecipes()
            .flowOn(Dispatchers.IO)
            .map { recipes ->
                recipes.map { it.toRecipe() }
            }
            .catch { e ->
                updateUiState {
                    copy(error = e.message ?: "An error occurred", isLoading = false)
                }
            }
            .onEach { recipes ->
                updateUiState {
                    copy(recipes = recipes, isLoading = false)
                }
            }.launchIn(viewModelScope)
    }

}

object FavoriteScreenContract {
    data class UiState(
        val isLoading: Boolean = false,
        val recipes: List<Recipe> = emptyList(),
        val error: String = ""
    )

    sealed class UiAction {
        data class SelectRecipe(val id: Int) : UiAction()
    }

    sealed class UiEffect {
        data class GotoDetail(val id: Int) : UiEffect()
    }
}