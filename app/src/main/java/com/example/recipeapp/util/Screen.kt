package com.example.recipeapp.util

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main_screen")
    data object RecipeDetail: Screen("recipe_detail_screen")
    data object FavoriteScreen: Screen("favorite_screen")
    data object SearchScreen: Screen("search_screen")
}