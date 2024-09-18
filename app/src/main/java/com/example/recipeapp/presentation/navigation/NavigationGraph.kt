package com.example.recipeapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.MainScreen
import com.example.recipeapp.presentation.components.NoInternetScreen
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreen
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenViewModel
import com.example.recipeapp.presentation.home.category_section.categories.CategoryRecipes
import com.example.recipeapp.presentation.home.category_section.categories.CategoriesCarouselViewModel
import com.example.recipeapp.presentation.recipe_detail.DetailScreen
import com.example.recipeapp.presentation.recipe_detail.DetailViewModel
import com.example.recipeapp.presentation.search_screen.SearchScreen
import com.example.recipeapp.presentation.search_screen.SearchScreenViewModel
import com.example.recipeapp.util.ConnectivityObserver
import com.example.recipeapp.util.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    status: ConnectivityObserver.Status,
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
            val viewModel: FavoriteScreenViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect

            FavoriteScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navController = navController
            ) {
                navController.navigate(Screen.RecipeDetail.route + "/$it")
            }
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}"
        ) {
            val viewModel: DetailViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect

            DetailScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navController = navController
            )

        }
        composable(route = Screen.SearchScreen.route) {
            if (status != ConnectivityObserver.Status.Available)
                NoInternetScreen()
            else {
                val viewModel: SearchScreenViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val uiEffect = viewModel.uiEffect
                SearchScreen(
                    uiState = uiState,
                    uiEffect = uiEffect,
                    onAction = viewModel::onAction,
                    navController = navController
                ) { recipeId ->
                    navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                }
            }
        }
        composable(route = Screen.RecipeListScreenByCategory.route + "/{category}") {
            val category = it.arguments?.getString("category") ?: ""
            if (status != ConnectivityObserver.Status.Available) NoInternetScreen()
            else {
                val viewModel: CategoriesCarouselViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val uiEffect = viewModel.uiEffect
                CategoryRecipes(
                    uiState = uiState,
                    uiEffect = uiEffect,
                    onAction = viewModel::onAction,
                    category = category,
                    navController = navController
                )
            }
        }
    }
}

