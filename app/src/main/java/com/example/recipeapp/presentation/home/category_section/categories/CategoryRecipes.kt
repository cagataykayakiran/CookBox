package com.example.recipeapp.presentation.home.category_section.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.components.CategoryListItem
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRecipes(
    uiState: RecipeTypeContract.UiState,
    uiEffect: Flow<RecipeTypeContract.UiEffect>,
    onAction: (RecipeTypeContract.UiAction) -> Unit,
    modifier: Modifier = Modifier,
    category: String,
    navController: NavController,
) {

    LaunchedEffect(category) {
        onAction(RecipeTypeContract.UiAction.SelectCategory(category))
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = category,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back to main screen",
                            tint = MainColorPrimary
                        )
                    }
                })
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(uiState.data) { recipe ->
                    CategoryListItem(
                        recipe = recipe,
                        navController = navController
                    )
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
                AnimatedPreloader(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}


