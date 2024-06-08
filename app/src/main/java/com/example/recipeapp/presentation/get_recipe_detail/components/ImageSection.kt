package com.example.recipeapp.presentation.get_recipe_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipeapp.domain.model.RecipeDetail

@Composable
fun ImageSection(modifier: Modifier = Modifier, recipe: RecipeDetail) {
    Box(
        modifier = modifier
            .size(500.dp, 300.dp)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = "https://img.spoonacular.com/recipes/${recipe.id}-312x231.jpg",
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}