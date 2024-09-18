package com.example.recipeapp.presentation.favorite_screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.recipeapp.domain.model.Recipe

class FavScreenPreviewProvider : PreviewParameterProvider<FavoriteScreenContract.UiState> {
    override val values: Sequence<FavoriteScreenContract.UiState>
        get() = sequenceOf(
            FavoriteScreenContract.UiState(
                isLoading = false,
                recipes = listOf(
                    Recipe(
                        id = 1,
                        title = "title",
                        image = "image",
                    )
                ),
                error = ""
            ),
            FavoriteScreenContract.UiState(
                isLoading = true,
                recipes = emptyList(),
                error = ""
            ),
            FavoriteScreenContract.UiState(
                isLoading = false,
                recipes = emptyList(),
                error = "error"
            )
        )
}