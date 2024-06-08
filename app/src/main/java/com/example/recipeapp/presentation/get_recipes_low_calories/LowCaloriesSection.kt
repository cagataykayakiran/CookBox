package com.example.recipeapp.presentation.get_recipes_low_calories


import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.ItemList

@Composable
fun LowCaloriesSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    lowCaloriesViewModel: LowCaloriesViewModel = hiltViewModel(),
) {

    val lowCaloriesState by lowCaloriesViewModel.lowCaloriesState.collectAsState()

    LazyRow(
        modifier = modifier,
    ) {
        items(lowCaloriesState.data) { recipe ->
            ItemList(
                recipe = recipe,
                navController = navController,
            )
        }
    }
}
