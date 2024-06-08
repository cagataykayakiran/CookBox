package com.example.recipeapp.presentation.get_recipes_high_protein

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
fun HighProteinSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HighProteinViewModel = hiltViewModel()
) {

    val highProteinState by viewModel.highProteinState.collectAsState()

    LazyRow(
        modifier = modifier
    ) {
        items(highProteinState.data) { recipe ->
            ItemList(
                recipe = recipe,
                navController = navController,
            )
        }
    }
}