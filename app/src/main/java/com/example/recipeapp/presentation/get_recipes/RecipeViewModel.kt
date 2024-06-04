package com.example.recipeapp.presentation.get_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.Resource
import com.example.recipeapp.domain.use_cases.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipes: GetRecipes
): ViewModel() {

    private val _recipesState = MutableStateFlow(RecipeState())
    val recipesState = _recipesState.asStateFlow()

    init {
        getRecipeList()
        println(recipesState.value.recipe.toString())

    }
    private fun getRecipeList() {
        getRecipes().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _recipesState.value = _recipesState.value.copy(recipe = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _recipesState.value = _recipesState.value.copy(isLoading = true)
                }
                is Resource.Error -> {
                    _recipesState.value = _recipesState.value.copy(error = result.message ?: "Error!")
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}