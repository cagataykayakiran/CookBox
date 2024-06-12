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
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.get_recipes_card.CardSliderSection
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.AppSearchBar
import com.example.recipeapp.presentation.components.AppTopBar
import com.example.recipeapp.presentation.components.TitleText
import com.example.recipeapp.presentation.get_category_recipes.CategorySection
import com.example.recipeapp.presentation.get_recipe_detail.RecipeDetailViewModel
import com.example.recipeapp.presentation.get_recipes_high_protein.HighProteinSection
import com.example.recipeapp.presentation.get_recipes_low_calories.LowCaloriesSection
import com.example.recipeapp.presentation.get_recipes_low_ready_time.LowReadyTimeCategorySection


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    detailViewModel: RecipeDetailViewModel = hiltViewModel(),
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
                    CardSliderSection(
                        detailViewModel = detailViewModel,
                        navController = navController
                    )
                }
                item {
                    TitleText(text = "Categories", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    CategorySection(navController = navController)
                }
                item {
                    TitleText(text = "Low Calories", fontWeight = FontWeight.SemiBold)
                    LowCaloriesSection(navController = navController)
                }
                item {
                    TitleText(text = "Low Ready Time", fontWeight = FontWeight.SemiBold)
                    LowReadyTimeCategorySection(navController = navController)
                }
                item {
                    TitleText(text = "High Protein", fontWeight = FontWeight.SemiBold)
                    HighProteinSection(navController = navController)
                }
            }
        }
    }
}

