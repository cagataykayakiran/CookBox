package com.example.recipeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.get_recipe_detail.DetailEvent
import com.example.recipeapp.presentation.get_recipe_detail.RecipeDetailViewModel
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.util.Screen

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .size(130.dp, 215.dp)
            .background(BackgroundPrimary)
            .clickable {
                onRecipeClick(recipe, navController, viewModel)
            }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            ) {
                AsyncImage(
                    model = recipe.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BodyText(
                    text = recipe.title,
                    maxLines = 2
                )
            }
        }
    }
}

fun onRecipeClick(
    recipe: Recipe,
    navController: NavController,
    viewModel: RecipeDetailViewModel
) {
    viewModel.onEvent(DetailEvent.SelectRecipe(recipe.id))
    navController.navigate(Screen.RecipeDetail.route + "/${recipe.id}")
}