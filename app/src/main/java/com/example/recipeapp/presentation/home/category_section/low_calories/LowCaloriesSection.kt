package com.example.recipeapp.presentation.home.category_section.low_calories

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
fun LowCaloriesSection(
    uiState: LowCaloriesContract.UiState,
    uiEffect: Flow<LowCaloriesContract.UiEffect>,
    onAction: (LowCaloriesContract.UiAction) -> Unit,
    onNavigateDetail: (Int) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is LowCaloriesContract.UiEffect.GoToDetail -> {
                    onNavigateDetail(effect.recipeId)
                }
            }
        }
    }

    LowCaloriesSectionContent(
        uiState = uiState,
        onAction = onAction,
    )
}

@Composable
private fun LowCaloriesSectionContent(
    modifier: Modifier = Modifier,
    uiState: LowCaloriesContract.UiState,
    onAction: (LowCaloriesContract.UiAction) -> Unit,
) {
    LazyRow(
        modifier = modifier
    ) {
        items(uiState.data) { recipe ->
            ItemList(
                recipe = recipe,
                onRecipeClick = { onAction(LowCaloriesContract.UiAction.OnRecipeClick(recipe)) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LowScreenPreview(
    @PreviewParameter(LowCaloriesSectionPreviewProvider::class) uiState: LowCaloriesContract.UiState,
) {
    LowCaloriesSectionContent(
        uiState = uiState,
        onAction = {},
    )
}