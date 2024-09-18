package com.example.recipeapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.AppSearchBar
import com.example.recipeapp.presentation.components.AppTopBar
import com.example.recipeapp.presentation.components.TitleText
import com.example.recipeapp.presentation.home.card_section.CardSection
import com.example.recipeapp.presentation.home.card_section.CardSectionViewModel
import com.example.recipeapp.presentation.home.category_section.categories.CategoriesCarouselViewModel
import com.example.recipeapp.presentation.home.category_section.categories.CategorySection
import com.example.recipeapp.presentation.home.category_section.high_protein.HighProteinSection
import com.example.recipeapp.presentation.home.category_section.high_protein.HighProteinViewModel
import com.example.recipeapp.presentation.home.category_section.low_calories.LowCaloriesSection
import com.example.recipeapp.presentation.home.category_section.low_calories.LowCaloriesViewModel
import com.example.recipeapp.presentation.home.category_section.low_ready_time.LowReadyTimeSection
import com.example.recipeapp.presentation.home.category_section.low_ready_time.LowReadyTimeViewModel
import com.example.recipeapp.util.Screen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var showAnimation by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2250)
        showAnimation = false
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController = navController) },
        containerColor = Color.White
    ) { innerPadding ->
        if (showAnimation) {
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedPreloader()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    AppSearchBar(navController = navController)
                }
                item {
                    TitleText(text = "Popular", fontWeight = FontWeight.SemiBold)
                    val viewModel: CardSectionViewModel = hiltViewModel()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val uiEffect = viewModel.uiEffect
                    CardSection(
                        uiState = uiState,
                        uiEffect = uiEffect,
                        onAction = viewModel::onAction,
                        onNavigateDetail = { recipeId ->
                            navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                        },
                    )
                }
                item {
                    TitleText(text = "Categories", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    val viewModel: CategoriesCarouselViewModel = hiltViewModel()
                    val uiEffect = viewModel.uiEffect
                    val categories = viewModel.categories
                    val images = viewModel.images
                    CategorySection(
                        uiEffect = uiEffect,
                        onAction = viewModel::onAction,
                        onNavigateRecipeList = { category ->
                            navController.navigate(Screen.RecipeListScreenByCategory.route + "/$category")
                        },
                        categories = categories,
                        images = images
                    )
                }
                item {
                    TitleText(text = "Low Calories", fontWeight = FontWeight.SemiBold)
                    val viewModel: LowCaloriesViewModel = hiltViewModel()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val uiEffect = viewModel.uiEffect
                    LowCaloriesSection(
                        uiState = uiState,
                        uiEffect = uiEffect,
                        onAction = viewModel::onAction
                    ) { recipeId ->
                        navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                    }
                }
                item {
                    TitleText(text = "Low Ready Time", fontWeight = FontWeight.SemiBold)
                    val viewModel: LowReadyTimeViewModel = hiltViewModel()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val uiEffect = viewModel.uiEffect
                    LowReadyTimeSection(
                        uiState = uiState,
                        uiEffect = uiEffect,
                        onAction = viewModel::onAction
                    ) {
                        navController.navigate(Screen.RecipeDetail.route + "/$it")
                    }
                }
                item {
                    TitleText(text = "High Protein", fontWeight = FontWeight.SemiBold)
                    val viewModel: HighProteinViewModel = hiltViewModel()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val uiEffect = viewModel.uiEffect
                    HighProteinSection(
                        uiState = uiState,
                        uiEffect = uiEffect,
                        onAction = viewModel::onAction
                    ) {
                        navController.navigate(Screen.RecipeDetail.route + "/$it")
                    }
                }
            }
        }
    }
}

