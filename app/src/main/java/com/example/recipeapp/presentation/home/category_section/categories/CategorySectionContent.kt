package com.example.recipeapp.presentation.home.category_section.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.recipeapp.presentation.home.category_section.categories.components.CategoriesCarouselList
import kotlinx.coroutines.flow.Flow

@Composable
fun CategorySection(
    uiEffect: Flow<RecipeTypeContract.UiEffect>,
    onAction: (RecipeTypeContract.UiAction) -> Unit,
    onNavigateRecipeList: (String) -> Unit,
    categories: List<String>,
    images: List<Int>,
) {
    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is RecipeTypeContract.UiEffect.GoToList -> {
                    onNavigateRecipeList(effect.category)
                }
            }
        }
    }

    CategorySectionContent(
        categories = categories,
        images = images,
        onAction = onAction,
        onCategoryClick = { index ->
            val selectedCategory = categories[index]
            onNavigateRecipeList(selectedCategory)
        }
    )
}

@Composable
private fun CategorySectionContent(
    categories: List<String>,
    images: List<Int>,
    onAction: (RecipeTypeContract.UiAction) -> Unit,
    onCategoryClick: (Int) -> Unit,
) {
    CategoriesCarouselList(
        categories = categories,
        images = images,
        onAction = onAction,
        onCategoryClick = onCategoryClick
    )
}