package com.example.recipeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HealthAndSafety
import androidx.compose.material.icons.rounded.HourglassTop
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.ui.theme.MainColorSecondary
import com.example.recipeapp.presentation.ui.theme.futuraSansFamily

@Composable
fun CategoryListItem(modifier: Modifier = Modifier, recipe: Recipe) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .size(400.dp, 250.dp)
            .background(MainColorSecondary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            ) {
                AsyncImage(
                    model = "https://img.spoonacular.com/recipes/${recipe.id}-636x393.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = recipe.title,
                    fontFamily = futuraSansFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Star",
                    )
                    Text(
                        text = recipe.aggregateLikes.toString(),
                        fontFamily = futuraSansFamily,
                    )
                    Icon(
                        imageVector = Icons.Rounded.HourglassTop,
                        contentDescription = "HourglassTop",
                    )
                    Text(
                        text = recipe.readyInMinutes.toString(),
                        fontFamily = futuraSansFamily,
                    )
                    Icon(
                        imageVector = Icons.Rounded.HealthAndSafety,
                        contentDescription = "HealthAndSafety"
                    )
                    Text(
                        text = recipe.healthScore.toString(),
                        fontFamily = futuraSansFamily,
                    )
                    Icon(
                        imageVector = Icons.Rounded.PlayCircle,
                        contentDescription = "HealthAndSafety"
                    )
                    Text(
                        text = recipe.servings.toString(),
                        fontFamily = futuraSansFamily,
                    )
                }
            }
        }
    }
}