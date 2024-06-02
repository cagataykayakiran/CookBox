package com.example.recipeapp.presentation.get_category_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.Resource
import com.example.recipeapp.domain.use_cases.GetTypeByRecipes
import com.example.recipeapp.presentation.get_recipes.RecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeTypeViewModel @Inject constructor(
    private val getTypeByRecipes: GetTypeByRecipes
): ViewModel() {

    private val _typeByRecipeState = MutableStateFlow(RecipeState())
    val typeByRecipeState = _typeByRecipeState.asStateFlow()

    private fun getType(diet: String) {
        getTypeByRecipes(diet).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _typeByRecipeState.value = RecipeState(recipe = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _typeByRecipeState.value = RecipeState(isLoading = true)
                }
                is Resource.Error -> {
                    _typeByRecipeState.value = RecipeState(error = result.message ?: "Error")
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.SelectCategory -> getType(event.category)
        }
    }
}