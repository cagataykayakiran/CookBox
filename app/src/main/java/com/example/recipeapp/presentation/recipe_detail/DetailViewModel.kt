package com.example.recipeapp.presentation.recipe_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.use_cases.GetRecipeById
import com.example.recipeapp.domain.use_cases.SaveDatabaseRecipe
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.presentation.recipe_detail.DetailContract.UiAction
import com.example.recipeapp.presentation.recipe_detail.DetailContract.UiEffect
import com.example.recipeapp.presentation.recipe_detail.DetailContract.UiState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val recipeById: GetRecipeById,
    private val saveDatabaseRecipe: SaveDatabaseRecipe,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    init {
        savedStateHandle.get<String>("recipeId")?.let { id ->
            Log.d("DetailViewModel", "Recipe ID: $id")
            onAction(UiAction.SelectRecipe(id.toInt()))
        }
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.SelectRecipe -> {
                Log.d("DetailViewModel", "Selected Recipe ID: ${uiAction.id}")
                getRecipe(uiAction.id)
            }

            is UiAction.OnFavouriteClick -> {
                saveFavoriteRecipe(uiAction.recipe, uiAction.recipe.extendedIngredients)
            }
        }
    }

    private fun saveFavoriteRecipe(recipe: RecipeDetail, ingredients: List<ExtendedIngredient>) {
        viewModelScope.launch {
            saveDatabaseRecipe(
                recipe = recipe,
                ingredients = ingredients,
                isFavorite = !recipe.isFavorite
            )
            updateUiState {
                copy(recipe = recipe.copy(isFavorite = !recipe.isFavorite))
            }
        }
    }

    private fun getRecipe(id: Int) {
        recipeById(id)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        updateUiState {
                            copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        updateUiState {
                            copy(recipe = result.data, isLoading = false)
                        }
                        Log.d("DetailViewModel", "Recipe: ${result.data}")
                    }

                    is Resource.Error -> {
                        updateUiState {
                            copy(
                                error = result.message ?: "An unexpected error occurred",
                                isLoading = false
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}

object DetailContract {
    data class UiState(
        val isLoading: Boolean = false,
        val recipe: RecipeDetail? = null,
        val error: String = ""
    )

    sealed class UiAction {
        data class SelectRecipe(val id: Int) : UiAction()
        data class OnFavouriteClick(val recipe: RecipeDetail) : UiAction()
    }

    sealed class UiEffect {
        data class ShowSnackBar(val message: String) : UiEffect()
    }
}