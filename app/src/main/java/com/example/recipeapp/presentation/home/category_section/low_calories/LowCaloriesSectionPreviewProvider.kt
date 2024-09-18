package com.example.recipeapp.presentation.home.category_section.low_calories

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.recipeapp.domain.model.Recipe

class LowCaloriesSectionPreviewProvider : PreviewParameterProvider<LowCaloriesContract.UiState> {
    override val values: Sequence<LowCaloriesContract.UiState>
        get() = sequenceOf(
            LowCaloriesContract.UiState(
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
            LowCaloriesContract.UiState(
                isLoading = true,
                data = emptyList(),
                error = ""
            ),
            LowCaloriesContract.UiState(
                isLoading = false,
                data = emptyList(),
                error = "error"
            )
        )
}