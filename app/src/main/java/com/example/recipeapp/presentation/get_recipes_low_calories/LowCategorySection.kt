package com.example.recipeapp.presentation.get_recipes_low_calories


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.presentation.get_recipes_low_calories.components.LowCategoryItem
@Composable
fun LowCategorySection(
    modifier: Modifier = Modifier,
    viewModel: LowCaloriesViewModel = hiltViewModel()
) {
    val lowCalorieState by viewModel.lowCaloriesState.collectAsState()

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(lowCalorieState.recipe) { recipe ->
            LowCategoryItem(recipe = recipe)
        }
    }
}
