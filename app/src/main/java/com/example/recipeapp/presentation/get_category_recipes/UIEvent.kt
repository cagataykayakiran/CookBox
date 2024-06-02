package com.example.recipeapp.presentation.get_category_recipes

sealed class UIEvent {
    data class SelectCategory(val category: String) : UIEvent()
}