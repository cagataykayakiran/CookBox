package com.example.recipeapp.presentation.get_recipes_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.util.Resource
import com.example.recipeapp.domain.use_cases.GetPopularRecipes
import com.example.recipeapp.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeCardViewModel @Inject constructor(
    private val getPopularRecipes: GetPopularRecipes
) : ViewModel() {

    private val _recipesState = MutableStateFlow(MainState.CardRecipeState())
    val recipesState = _recipesState.asStateFlow()

    init {
        getRecipeList()
    }

    private fun getRecipeList() {
        getPopularRecipes().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _recipesState.value = _recipesState.value.copy(
                        data = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _recipesState.value = _recipesState.value.copy(isLoading = true)
                }

                is Resource.Error -> {
                    _recipesState.value =
                        _recipesState.value.copy(
                            error = result.message ?: "Error!",
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }
}