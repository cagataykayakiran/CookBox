package com.example.recipeapp.presentation.home.category_section.low_ready_time

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.recipeapp.domain.model.Recipe

class LowReadyTimeSectionPreviewProvider : PreviewParameterProvider<LowReadyTimeContract.UiState> {
    override val values: Sequence<LowReadyTimeContract.UiState>
        get() = sequenceOf(
            LowReadyTimeContract.UiState(
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
            LowReadyTimeContract.UiState(
                isLoading = true,
                data = emptyList(),
                error = ""
            ),
            LowReadyTimeContract.UiState(
                isLoading = false,
                data = emptyList(),
                error = "error"
            )
        )
}