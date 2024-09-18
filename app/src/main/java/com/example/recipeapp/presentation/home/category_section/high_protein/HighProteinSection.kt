package com.example.recipeapp.presentation.home.category_section.high_protein

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
fun HighProteinSection(
    uiState: HighProteinContract.UiState,
    uiEffect: Flow<HighProteinContract.UiEffect>,
    onAction: (HighProteinContract.UiAction) -> Unit,
    onNavigateDetail: (Int) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is HighProteinContract.UiEffect.GoToDetail -> {
                    onNavigateDetail(effect.recipeId)
                }
            }
        }
    }

    HighProteinSectionContent(
        uiState = uiState,
        onAction = onAction,
    )
}

@Composable
private fun HighProteinSectionContent(
    modifier: Modifier = Modifier,
    uiState: HighProteinContract.UiState,
    onAction: (HighProteinContract.UiAction) -> Unit,
) {
    LazyRow(
        modifier = modifier
    ) {
        items(uiState.data) { recipe ->
            ItemList(
                recipe = recipe,
                onRecipeClick = { onAction(HighProteinContract.UiAction.OnRecipeClick(recipe)) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HighScreenPreview(
    @PreviewParameter(HighProteinSectionPreviewProvider::class) uiState: HighProteinContract.UiState,
) {
    HighProteinSectionContent(
        uiState = uiState,
        onAction = {},
    )
}