package com.example.recipeapp.presentation.home.card_section

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.recipeapp.domain.model.Recipe

class CardSectionPreviewProvider : PreviewParameterProvider<CardContract.UiState> {
    override val values: Sequence<CardContract.UiState>
        get() = sequenceOf(
            CardContract.UiState(
                isLoading = false,
                data = listOf(
                    Recipe(
                        id = 1,
                        title = "title",
                        image = "image",
                    ),
                    Recipe(
                        id = 2,
                        title = "title",
                        image = "image",
                    ),
                    Recipe(
                        id = 3,
                        title = "title",
                        image = "image",
                    ),
                ),
                error = ""
            ),
            CardContract.UiState(
                isLoading = true,
                data = emptyList(),
                error = ""
            ),
            CardContract.UiState(
                isLoading = false,
                data = emptyList(),
                error = "error"
            )
        )
}