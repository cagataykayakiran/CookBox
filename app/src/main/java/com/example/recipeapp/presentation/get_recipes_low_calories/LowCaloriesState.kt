package com.example.recipeapp.presentation.get_recipes_low_calories

import com.example.recipeapp.domain.model.Recipe

data class LowCaloriesState(
    val isLoading: Boolean = false,
    val recipe: List<Recipe> = emptyList(),
    val error: String = ""
)
