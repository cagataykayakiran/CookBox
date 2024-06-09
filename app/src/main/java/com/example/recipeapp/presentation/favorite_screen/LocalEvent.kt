package com.example.recipeapp.presentation.favorite_screen

import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.RecipeDetail

sealed class LocalEvent {
    data class SaveRecipe(
        val recipeDetail: RecipeDetail,
        val ingredients: List<ExtendedIngredient>
    ) : LocalEvent()
}