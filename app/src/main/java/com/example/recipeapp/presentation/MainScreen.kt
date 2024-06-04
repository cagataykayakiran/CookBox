package com.example.recipeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.card_slider_section.CardSliderSection
import com.example.recipeapp.presentation.components.AppBottomBar
import com.example.recipeapp.presentation.components.AppSearchBar
import com.example.recipeapp.presentation.components.AppTopBar
import com.example.recipeapp.presentation.get_category_recipes.CategorySection
import com.example.recipeapp.presentation.get_category_recipes.RecipeTypeViewModel
import com.example.recipeapp.presentation.get_recipes.RecipeViewModel
import com.example.recipeapp.presentation.get_recipes_low_calories.LowCategorySection
import com.example.recipeapp.presentation.ui.theme.futuraSansFamily


@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel = hiltViewModel(),
    typeViewModel: RecipeTypeViewModel = hiltViewModel()
) {
    val mainState by viewModel.recipesState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar() },
        containerColor = Color.White
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 17.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                AppSearchBar(navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 7.dp),
                    text = "Amazing Breakfast",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                CardSliderSection(
                    navController = navController,
                    cardItems = mainState.recipe,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 7.dp),
                    text = "Categories",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                Spacer(modifier = Modifier.height(10.dp))
                CategorySection(typeViewModel = typeViewModel, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 7.dp),
                    text = "Low Calories ",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    fontFamily = futuraSansFamily
                )
                Spacer(modifier = Modifier.height(10.dp))
                LowCategorySection()
            }
        }
    }
}

