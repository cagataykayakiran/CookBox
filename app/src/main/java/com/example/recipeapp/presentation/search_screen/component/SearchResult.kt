package com.example.recipeapp.presentation.search_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.util.Screen

@Composable
fun SearchResult(
    data: List<Recipe>,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(15.dp),
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(data) { recipe ->
            SearchItem(recipe = recipe) {
                navController.navigate(Screen.RecipeDetail.route + "/${recipe.id}")
            }
        }
    }
}
