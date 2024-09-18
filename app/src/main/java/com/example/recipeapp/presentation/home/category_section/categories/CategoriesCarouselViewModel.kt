package com.example.recipeapp.presentation.home.category_section.categories

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.util.Resource
import com.example.recipeapp.domain.use_cases.GetTypeByRecipes
import com.example.recipeapp.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.recipeapp.presentation.home.category_section.categories.RecipeTypeContract.UiState
import com.example.recipeapp.presentation.home.category_section.categories.RecipeTypeContract.UiEffect
import com.example.recipeapp.presentation.home.category_section.categories.RecipeTypeContract.UiAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class CategoriesCarouselViewModel @Inject constructor(
    private val typeByRecipes: GetTypeByRecipes
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    val categories = listOf("Gluten Free", "Ketogenic", "Pescetarian", "Vegan", "Vegetarian")
    val images = listOf(
        R.drawable.glutenfree,
        R.drawable.ketogenic,
        R.drawable.pescatarian,
        R.drawable.vegan,
        R.drawable.salad_icon
    )

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.SelectCategory -> {
                viewModelScope.launch {
                    Log.d("CategoriesCarouselViewModel", "Selected category: ${uiAction.category}")
                    getType(uiAction.category)
                }
            }
        }
    }

    private fun getType(category: String) {
        typeByRecipes(category)
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
                        emitUiEffect(UiEffect.GoToList(category))
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
                                error = result.message ?: "Error occurred",
                                isLoading = false
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }
}

object RecipeTypeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val data: List<Recipe> = emptyList(),
        val error: String = ""
    )

    sealed class UiAction {
        data class SelectCategory(val category: String) : UiAction()
    }

    sealed class UiEffect {
        data class GoToList(val category: String) : UiEffect()
    }
}