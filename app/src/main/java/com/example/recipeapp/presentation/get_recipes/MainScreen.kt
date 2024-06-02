package com.example.recipeapp.presentation.get_recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.presentation.components.AppSearchBar
import com.example.recipeapp.presentation.get_category_recipes.CategoryList
import com.example.recipeapp.presentation.ui.theme.futuraSansFamily


@Composable
fun MainScreen(modifier: Modifier = Modifier, state: RecipeState) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 17.dp, end = 17.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppSearchBar()
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, start = 7.dp),
                        text = "Amazing Breakfast",
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontFamily = futuraSansFamily
                    )
                    CardSliderSection(cardItems = state.recipe)
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, start = 7.dp),
                        text = "Categories",
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontFamily = futuraSansFamily
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    val categories = listOf("Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Ovo-Vegetarian", "Vegan")
                    val images = listOf(R.drawable.fish, R.drawable.chicken, R.drawable.vegetable, R.drawable.vegetable, R.drawable.vegetable, R.drawable.vegetable, R.drawable.vegetable)
                    CategoryList(categories = categories, images = images)
                }
            }
        }
}
