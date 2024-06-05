package com.example.recipeapp.presentation.get_recipes_low_ready_time


import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.presentation.components.ItemList

@Composable
fun LowReadyTimeCategorySection(
    modifier: Modifier = Modifier,
    viewModel: LowReadyTimeViewModel = hiltViewModel()
) {
    val lowReadyTimeState by viewModel.lowReadyTimeState.collectAsState()

    LazyRow(
        modifier = modifier,
    ) {
        items(lowReadyTimeState.data) { recipe ->
            ItemList(recipe = recipe)
        }
    }
}
