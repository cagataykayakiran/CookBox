package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.MainScreen
import com.example.recipeapp.presentation.components.NoInternetScreen
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreen
import com.example.recipeapp.presentation.get_category_recipes.RecipeScreenByCategory
import com.example.recipeapp.presentation.get_category_recipes.RecipeTypeViewModel
import com.example.recipeapp.presentation.get_recipe_detail.RecipeDetailScreen
import com.example.recipeapp.presentation.search_screen.SearchScreen
import com.example.recipeapp.util.ConnectivityObserver
import com.example.recipeapp.util.Screen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    viewModel: RecipeTypeViewModel = hiltViewModel(),
    status: ConnectivityObserver.Status

) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination =
        if (status == ConnectivityObserver.Status.Available)
            Screen.MainScreen.route else Screen.FavoriteScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            if (status != ConnectivityObserver.Status.Available)
                NoInternetScreen()
             else MainScreen(navController = navController)
        }
        composable(route = Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController)
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}"
        ) {
            RecipeDetailScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            if (status != ConnectivityObserver.Status.Available)
                NoInternetScreen()
             else SearchScreen(navController = navController)
        }
        composable(route = Screen.RecipeListScreenByCategory.route + "/{category}") {
            val category = it.arguments?.getString("category") ?: ""
            if (status != ConnectivityObserver.Status.Available)
                NoInternetScreen()
            else RecipeScreenByCategory(
                viewModel = viewModel,
                category = category,
                navController = navController
            )
        }
    }
}

