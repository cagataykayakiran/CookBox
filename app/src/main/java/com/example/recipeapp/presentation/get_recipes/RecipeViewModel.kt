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
        getRecipeList(diet = "Vegetarian")
        println(recipesState.value.recipe.toString())

    }
    private fun getRecipeList(diet: String) {
        getRecipes(diet).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _recipesState.value = RecipeState(recipe = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _recipesState.value = RecipeState(isLoading = true)
                }
                is Resource.Error -> {
                    _recipesState.value = RecipeState(error = result.message ?: "Error")
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}