package com.example.recipeapp.presentation.get_category_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.util.Resource
import com.example.recipeapp.domain.use_cases.GetTypeByRecipes
import com.example.recipeapp.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeTypeViewModel @Inject constructor(
    private val getTypeByRecipes: GetTypeByRecipes
) : ViewModel() {

    private val _typeByRecipeState = MutableStateFlow(MainState.RecipeTypeState())
    val typeByRecipeState = _typeByRecipeState.asStateFlow()

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.SelectCategory -> getType(event.category)
        }
    }

    private fun getType(diet: String) {
        getTypeByRecipes(diet).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _typeByRecipeState.value = _typeByRecipeState.value.copy(
                        data = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _typeByRecipeState.value = _typeByRecipeState.value.copy(
                       isLoading = true
                    )
                }
                is Resource.Error -> {
                    _typeByRecipeState.value = _typeByRecipeState.value.copy(
                        error = result.message ?: "Error",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}