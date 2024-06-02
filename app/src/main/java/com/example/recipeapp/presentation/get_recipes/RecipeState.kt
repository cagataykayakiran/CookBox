package com.example.recipeapp.presentation.get_recipes

import com.example.recipeapp.domain.model.Recipe
data class RecipeState(
    val isLoading: Boolean = false,
    val recipe: List<Recipe> = emptyList(),
    val error: String = ""
)