package com.example.recipeapp.presentation.search_screen.presentation

import com.example.recipeapp.domain.model.Recipe

data class SearchScreenState (
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val searchList: List<Recipe> = emptyList(),
    val error: String? = null
)