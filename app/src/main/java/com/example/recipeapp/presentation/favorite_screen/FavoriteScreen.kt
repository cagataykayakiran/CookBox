package com.example.recipeapp.presentation.favorite_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.NavigationIcon
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenContract.UiAction
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenContract.UiState
import com.example.recipeapp.presentation.favorite_screen.FavoriteScreenContract.UiEffect
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoriteScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navController: NavController,
    onNavigateDetail: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is UiEffect.GotoDetail -> {
                    onNavigateDetail(effect.id)
                }
            }
        }

    }

    FavoriteScreenContent(
        uiState = uiState,
        onAction = onAction,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = { AppBottomBar(navController = navController) },
        topBar = {
            TopAppBar(
                { Text(text = "Favorite Screen") },
                navigationIcon = { NavigationIcon(navController = navController) }
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(uiState.recipes) { recipe ->
                    Box(
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .shadow(10.dp)
                            .background(BackgroundPrimary)
                            .clickable {
                                onAction(UiAction.SelectRecipe(recipe.id))
                            },
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = recipe.image,
                                contentDescription = recipe.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(150.dp)
                            )
                            Text(text = recipe.title, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
            if (uiState.error.isNotBlank()) {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (uiState.isLoading) {
                AnimatedPreloader()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavScreenPreview(
    @PreviewParameter(FavScreenPreviewProvider::class) uiState: UiState,
) {
    FavoriteScreenContent(
        uiState = uiState,
        onAction = {},
        navController = NavController(LocalContext.current),
    )
}