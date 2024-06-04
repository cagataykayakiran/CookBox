package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.MainScreen
import com.example.recipeapp.presentation.get_category_recipes.RecipeListScreenByCategoryScreen
import com.example.recipeapp.presentation.get_category_recipes.RecipeTypeViewModel
import com.example.recipeapp.presentation.search_screen.SearchScreen
import com.example.recipeapp.util.Screen

@Composable
fun NavigationGraph(modifier: Modifier = Modifier, viewModel: RecipeTypeViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.FavoriteScreen.route) {
        }
        composable(route = Screen.RecipeDetail.route) {

        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen()
        }
        composable(route = Screen.RecipeListScreenByCategory.route + "/{category}") {
            val category = it.arguments?.getString("category") ?: ""
            RecipeListScreenByCategoryScreen(category = category, viewModel = viewModel, navController = navController)
        }
    }
}
