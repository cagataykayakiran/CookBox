package com.example.recipeapp.presentation.search_screen.presentation

import com.example.recipeapp.domain.model.Recipe

sealed class SearchUiEvents {
    data class OnSearchQueryChanged(val query: String) : SearchUiEvents()
    data class OnSearchedItemClick(val recipe: Recipe) : SearchUiEvents()
    data object OnSearchedResetClick : SearchUiEvents()
}
