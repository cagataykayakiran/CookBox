package com.example.recipeapp.presentation.recipe_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import be.digitalia.compose.htmlconverter.htmlToString
import coil.compose.AsyncImage
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.presentation.components.BodyText
import com.example.recipeapp.presentation.components.TitleText
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary

@Composable
fun IngredientSection(recipe: RecipeDetail) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        TitleText(text = "Instructions")
        Text(text = htmlToString(recipe.instructions), color = Color.Black)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            TitleText(text = "Ingredients")
            recipe.extendedIngredients.forEach { ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BackgroundPrimary),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = "https://img.spoonacular.com/ingredients_250x250/${ingredient.image}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    ) {
                        BodyText(text = ingredient.original)
                    }
                }
            }
        }
    }
}