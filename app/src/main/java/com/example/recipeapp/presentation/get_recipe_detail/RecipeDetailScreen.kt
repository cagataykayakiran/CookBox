package com.example.recipeapp.presentation.get_recipe_detail


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.NavigationIcon
import com.example.recipeapp.presentation.favorite_screen.DatabaseViewModel
import com.example.recipeapp.presentation.favorite_screen.LocalEvent
import com.example.recipeapp.presentation.get_recipe_detail.components.ImageSection
import com.example.recipeapp.presentation.get_recipe_detail.components.InformationSection
import com.example.recipeapp.presentation.get_recipe_detail.components.IngredientSection
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    databaseViewModel: DatabaseViewModel = hiltViewModel()
) {
    val state by viewModel.recipeDetailState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Recipe Detail") },
                navigationIcon = { NavigationIcon(navController = navController) },
                actions = {
                    IconButton(onClick = {
                        state.recipe?.let { recipe ->
                            databaseViewModel.onEvent(
                                LocalEvent.SaveRecipe(
                                    recipeDetail = recipe,
                                    ingredients = recipe.extendedIngredients
                                )
                            )
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Recipe saved")
                            }
                        }
                    }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = MainColorPrimary
                        )
                    }
                }
            )
        },
        bottomBar = { AppBottomBar(navController = navController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            state.recipe?.let { recipe ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        ImageSection(recipe = recipe)
                    }
                    item {
                        InformationSection(recipe = recipe)
                    }
                    item {
                        IngredientSection(recipe = recipe)
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}



