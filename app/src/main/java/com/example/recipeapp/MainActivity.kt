package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.AppTopBar
import com.example.recipeapp.presentation.get_recipes.MainScreen
import com.example.recipeapp.presentation.get_recipes.RecipeViewModel
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.presentation.ui.theme.PaparaFinalCaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaparaFinalCaseTheme {
                val viewModel: RecipeViewModel = hiltViewModel()
                val state by viewModel.recipesState.collectAsState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = BackgroundPrimary ,
                    topBar = { AppTopBar() },
                    bottomBar = { AppBottomBar() }
                ) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding), state)
                }
            }
        }
    }
}
