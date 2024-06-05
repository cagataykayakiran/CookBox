package com.example.recipeapp.presentation.favorite_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AppBottomBar

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(
        modifier = modifier,
        bottomBar = { AppBottomBar(navController = navController)}
    ) {
        Text(text = "Favorite Screen", modifier = Modifier.padding(it))
    }
}