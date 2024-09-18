package com.example.recipeapp.presentation.home.category_section.low_ready_time

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.recipeapp.presentation.components.ItemList
import kotlinx.coroutines.flow.Flow

@Composable
fun LowReadyTimeSection(
    uiState: LowReadyTimeContract.UiState,
    uiEffect: Flow<LowReadyTimeContract.UiEffect>,
    onAction: (LowReadyTimeContract.UiAction) -> Unit,
    onNavigateDetail: (Int) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is LowReadyTimeContract.UiEffect.GoToDetail -> {
                    onNavigateDetail(effect.recipeId)
                }
            }
        }

    }

    LowReadyTimeSectionContent(
        uiState = uiState,
        onAction = onAction
    )
}

@Composable
private fun LowReadyTimeSectionContent(
    modifier: Modifier = Modifier,
    uiState: LowReadyTimeContract.UiState,
    onAction: (LowReadyTimeContract.UiAction) -> Unit,
) {
    LazyRow(
        modifier = modifier
    ) {
        items(uiState.data) { recipe ->
            ItemList(
                recipe = recipe,
                onRecipeClick = { onAction(LowReadyTimeContract.UiAction.OnRecipeClick(recipe)) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LowScreenPreview(
    @PreviewParameter(LowReadyTimeSectionPreviewProvider::class) uiState: LowReadyTimeContract.UiState,
) {
    LowReadyTimeSectionContent(
        uiState = uiState,
        onAction = {},
    )
}