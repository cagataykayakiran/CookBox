package com.example.recipeapp.presentation.search_screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SearchScreenPreviewProvider : PreviewParameterProvider<SearchScreenContract.UiState> {
    override val values: Sequence<SearchScreenContract.UiState>
        get() = sequenceOf(
            SearchScreenContract.UiState(
                isLoading = false,
                data = emptyList(),
                error = "",
                searchQuery = "",
                isSearching = true
            ),
            SearchScreenContract.UiState(
                isLoading = true,
                data = emptyList(),
                error = "",
                searchQuery = "",
            ),
            SearchScreenContract.UiState(
                isLoading = false,
                data = emptyList(),
                error = "",
                searchQuery = "test",
            ),
        )
}