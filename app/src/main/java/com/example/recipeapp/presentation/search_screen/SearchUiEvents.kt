package com.example.recipeapp.presentation.search_screen


sealed class SearchUiEvents {
    data class OnSearchQueryChanged(val query: String) : SearchUiEvents()
    data object OnSearchedResetClick : SearchUiEvents()
}
