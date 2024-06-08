package com.example.recipeapp.presentation.get_recipe_detail.components

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.digitalia.compose.htmlconverter.htmlToString
import coil.compose.AsyncImage
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary

@Composable
fun IngredientSection(recipe: RecipeDetail) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        CustomHeaderText(text = "Instructions")
        CustomText(
            text = remember(recipe.instructions) { htmlToString(recipe.instructions) })
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            CustomHeaderText(text = "Ingredients")
            recipe.extendedIngredients.forEach { ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BackgroundPrimary),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = "https://img.spoonacular.com/ingredients_250x250/${ingredient.image}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(BackgroundPrimary)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        CustomText(text = ingredient.original)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomText(
    text: String,
    fontWeight: FontWeight = FontWeight.Medium,
    fontSize: TextUnit = 15.sp
) {
    Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}
@Composable
fun CustomHeaderText(
    text: String,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 25.sp
) {
    Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}