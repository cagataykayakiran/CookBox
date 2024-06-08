package com.example.recipeapp.presentation.get_recipe_detail

import com.example.recipeapp.domain.model.RecipeDetail

data class RecipeDetailState(
    val recipe: RecipeDetail? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
