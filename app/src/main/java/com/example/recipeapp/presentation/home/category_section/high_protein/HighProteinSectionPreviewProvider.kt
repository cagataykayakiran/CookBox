package com.example.recipeapp.presentation.home.category_section.high_protein

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.recipeapp.domain.model.Recipe

class HighProteinSectionPreviewProvider : PreviewParameterProvider<HighProteinContract.UiState> {
    override val values: Sequence<HighProteinContract.UiState>
        get() = sequenceOf(
            HighProteinContract.UiState(
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
            HighProteinContract.UiState(
                isLoading = true,
                data = emptyList(),
                error = ""
            ),
            HighProteinContract.UiState(
                isLoading = false,
                data = emptyList(),
                error = "error"
            )
        )


}