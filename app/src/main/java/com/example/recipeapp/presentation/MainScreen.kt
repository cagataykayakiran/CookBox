package com.example.recipeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.get_recipes_card.CardSliderSection
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.AppSearchBar
import com.example.recipeapp.presentation.components.AppTopBar
import com.example.recipeapp.presentation.get_category_recipes.CategorySection
import com.example.recipeapp.presentation.get_recipe_detail.RecipeDetailViewModel
import com.example.recipeapp.presentation.get_recipes_high_protein.HighProteinSection
import com.example.recipeapp.presentation.get_recipes_low_calories.LowCaloriesSection
import com.example.recipeapp.presentation.get_recipes_low_ready_time.LowReadyTimeCategorySection
import com.example.recipeapp.presentation.ui.theme.futuraSansFamily


@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    detailViewModel: RecipeDetailViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { AppTopBar()},
        bottomBar = { AppBottomBar(navController = navController) },
        containerColor = Color.White
    ) { innerPadding ->
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
                Text(
                    text = "Popular",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                CardSliderSection(detailViewModel = detailViewModel, navController = navController)
            }
            item {
                Text(
                    text = "Categories",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                Spacer(modifier = Modifier.height(8.dp))
                CategorySection(navController = navController)
            }
            item {
                Text(
                    text = "Low Calories ",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                LowCaloriesSection(navController = navController)
            }
            item {
                Text(
                    text = "Low Ready Time",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                LowReadyTimeCategorySection(navController = navController)
            }
            item {
                Text(
                    text = "High Protein",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                HighProteinSection(navController = navController)
            }
        }
    }
}

