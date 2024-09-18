package com.example.recipeapp.presentation.recipe_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.RoomService
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import be.digitalia.compose.htmlconverter.htmlToString
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.presentation.components.BodyText
import com.example.recipeapp.presentation.components.TitleText
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InformationSection(modifier: Modifier = Modifier, recipe: RecipeDetail) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TitleText(text = recipe.title)
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InformationItem(
                icon = Icons.Default.HourglassTop,
                text = recipe.readyInMinutes.toString() + " min"
            )
            InformationItem(
                icon = Icons.Default.RoomService,
                text = recipe.servings.toString() + " servings"
            )
            InformationItem(
                icon = Icons.Default.Star,
                text = recipe.healthScore.toString() + " Health Score"
            )
        }
        TitleText(text = "About")
        Text(text = htmlToString(recipe.summary), color = Color.Black)
        TitleText(text = "Tags")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            recipe.dishTypes.forEach { tag ->
                RecipeTag(tag = tag)
            }
        }
    }
}

@Composable
fun InformationItem(modifier: Modifier = Modifier, icon: ImageVector, text: String) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = MainColorPrimary
        )
        BodyText(text = text, maxLines = 2)
    }
}

@Composable
fun RecipeTag(tag: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(MainColorPrimary)
            .padding(8.dp)
    ) {
        Text(
            text = tag,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}