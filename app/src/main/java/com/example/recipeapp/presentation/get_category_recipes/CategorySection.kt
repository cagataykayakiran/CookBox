package com.example.recipeapp.presentation.get_category_recipes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.get_category_recipes.components.CategoryList

@Composable
fun CategorySection(
    navController: NavController,
) {

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
}