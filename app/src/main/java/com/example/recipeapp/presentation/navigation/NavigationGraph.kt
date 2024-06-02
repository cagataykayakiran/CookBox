package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.MainScreen
import com.example.recipeapp.util.Screen

@Composable
fun NavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier, navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.FavoriteScreen.route) {
        }
        composable(route = Screen.RecipeDetail.route) {
            
        }
    }
}