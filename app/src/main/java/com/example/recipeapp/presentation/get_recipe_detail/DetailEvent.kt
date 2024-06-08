package com.example.recipeapp.presentation.get_recipe_detail

sealed class DetailEvent {
    data class SelectRecipe(val id: Int) : DetailEvent()
}