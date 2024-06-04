package com.example.recipeapp.presentation.get_category_recipes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.util.Screen

@Composable
fun CategoryList(
    categories: List<String>,
    images: List<Int>,
    navController: NavController,
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(categories.size) { index ->
            CategoryItem(
                name = categories[index],
                iconResId = images[index],
                isSelected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    navController.navigate(Screen.RecipeListScreenByCategory.route + "/${categories[index]}")
                }
            )
        }
    }
}