package com.example.recipeapp.presentation.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.local.entity.toRecipe
import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.use_cases.GetLocalRecipes
import com.example.recipeapp.domain.use_cases.SaveDatabaseRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val saveDatabaseRecipe: SaveDatabaseRecipe,
    private val getLocalRecipes: GetLocalRecipes,
) : ViewModel() {

    private val _recipes = MutableStateFlow(RecipeState())
    val recipes = _recipes.asStateFlow()

    init {
        viewModelScope.launch {
            _recipes.value = _recipes.value.copy(isLoading = true)
            delay(2000)
            loadRecipes()
        }
    }
    fun onEvent(event: LocalEvent) {
        when(event){
            is LocalEvent.FavouriteRecipe -> {
                toggleFavorite(event.recipeDetail, event.ingredients)
            }
        }
    }

    private fun toggleFavorite(recipe: RecipeDetail, ingredients: List<ExtendedIngredient>) {
        viewModelScope.launch {
            val isFavorite = recipe.isFavorite
            saveDatabaseRecipe(recipe = recipe, ingredients = ingredients, isFavorite = !isFavorite)
        }
    }

    private suspend fun loadRecipes() {
        getLocalRecipes().onEach { recipeEntities ->
            _recipes.value = _recipes.value.copy(
                recipes = recipeEntities.map { it.toRecipe() },
                isLoading = false
            )
        }.launchIn(viewModelScope)
    }
}