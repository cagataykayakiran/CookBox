package com.example.recipeapp.presentation.get_recipe_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.use_cases.GetRecipeById
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeById: GetRecipeById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipeDetailState = MutableStateFlow(RecipeDetailState())
    val recipeDetailState = _recipeDetailState.asStateFlow()

    init {
        savedStateHandle.get<String>("recipeId")?.let { id ->
            onEvent(DetailEvent.SelectRecipe(id.toInt()))
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.SelectRecipe -> {
                getRecipe(event.id)
            }
        }
    }

    private fun getRecipe(id: Int) {
        getRecipeById(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _recipeDetailState.value = RecipeDetailState(isLoading = true)
                    delay(2000)
                }

                is Resource.Success -> {
                    _recipeDetailState.value =
                        RecipeDetailState(recipe = result.data, isLoading = false)
                }

                is Resource.Error -> {
                    _recipeDetailState.value =
                        RecipeDetailState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }
}