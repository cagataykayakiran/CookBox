package com.example.recipeapp.presentation.recipe_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.NavigationIcon
import com.example.recipeapp.presentation.recipe_detail.components.ImageSection
import com.example.recipeapp.presentation.recipe_detail.components.InformationSection
import com.example.recipeapp.presentation.recipe_detail.components.IngredientSection
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    uiState: DetailContract.UiState,
    uiEffect: Flow<DetailContract.UiEffect>,
    onAction: (DetailContract.UiAction) -> Unit,
    navController: NavController
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is DetailContract.UiEffect.ShowSnackBar -> {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Recipe saved!")
                    }
                }
            }
        }
    }

    DetailScreenContent(
        uiState = uiState,
        onAction = onAction,
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreenContent(
    modifier: Modifier = Modifier,
    uiState: DetailContract.UiState,
    onAction: (DetailContract.UiAction) -> Unit,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    navController: NavController
) {
    Scaffold(
        snackbarHost = { CustomSnackBarHost(snackBarHostState) },
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Recipe Detail") },
                navigationIcon = { NavigationIcon(navController = navController) },
                actions = {
                    IconButton(onClick = {
                        uiState.recipe?.let { recipe ->
                            onAction(DetailContract.UiAction.OnFavouriteClick(recipe))
                            coroutineScope.launch {
                                if (recipe.isFavorite) {
                                    snackBarHostState.showSnackbar("Recipe removed from favorites")
                                } else {
                                    snackBarHostState.showSnackbar("Recipe saved to favorites")
                                }
                            }
                        }
                    }) {
                        if (uiState.recipe?.isFavorite == true) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                tint = MainColorPrimary
                            )
                        } else {
                            Icon(
                                Icons.Outlined.Favorite,
                                contentDescription = "Favorite",
                            )
                        }
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
            uiState.recipe?.let { recipe ->
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
                AnimatedPreloader(
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CustomSnackBarHost(snackBarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackBarHostState) { data ->
        // Snackbar için animasyon ekleyelim
        Snackbar(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    Color(0xFF323232),
                    shape = RoundedCornerShape(8.dp)
                ), // Arka plan rengi ve köşe yuvarlama
            content = {
                Text(
                    text = data.visuals.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White, // Yazı rengi
                    modifier = Modifier.padding(8.dp)
                )
            },
            action = {
                data.visuals.actionLabel?.let { actionLabel ->
                    TextButton(onClick = { data.performAction() }) {
                        Text(
                            text = actionLabel,
                            color = MainColorPrimary // Action butonunun rengi
                        )
                    }
                }
            }
        )
    }
}
