package com.example.recipeapp.presentation.get_category_recipes

import com.example.recipeapp.domain.model.Recipe

data class RecipeTypeState(
    val isLoading: Boolean = false,
    val recipe: List<Recipe> = emptyList(),
    val error: String = ""
)
