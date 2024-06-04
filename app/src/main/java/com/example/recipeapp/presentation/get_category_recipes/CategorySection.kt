package com.example.recipeapp.presentation.get_category_recipes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.get_category_recipes.components.CategoryList

@Composable
fun CategorySection(
    typeViewModel: RecipeTypeViewModel,
    navController: NavController,
) {
    val typeState by typeViewModel.typeByRecipeState.collectAsState()

    println("CategorySection" + typeState.recipe.size)
    val categories = listOf("Gluten Free", "Ketogenic", "Pescetarian", "Vegan", "Vegetarian")
    val images = listOf(
        R.drawable.glutenfree,
        R.drawable.ketogenic,
        R.drawable.pescatarian,
        R.drawable.vegan,
        R.drawable.salad_icon
    )

    CategoryList(
        categories = categories,
        images = images,
        navController = navController,
    )
    Spacer(modifier = Modifier.height(18.dp))
}