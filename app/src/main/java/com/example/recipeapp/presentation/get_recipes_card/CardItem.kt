package com.example.recipeapp.presentation.get_recipes_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.components.onRecipeClick
import com.example.recipeapp.presentation.get_recipe_detail.RecipeDetailViewModel
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import com.example.recipeapp.presentation.ui.theme.MainColorSecondary

@Composable
fun CardItem(recipe: Recipe, navController: NavController, detailViewModel: RecipeDetailViewModel) {
    Card(
        modifier = Modifier
            .size(370.dp, 150.dp)
            .fillMaxHeight()
            .clickable {
                onRecipeClick(
                    recipe = recipe,
                    navController = navController,
                    viewModel = detailViewModel
                )
            },
        colors = CardDefaults.cardColors(containerColor = MainColorSecondary),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )
                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MainColorPrimary,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    maxLines = 1
                )
            }
            val colorWhite = Color(0x4DFFFFFF)
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(110.dp, 110.dp)
                    .background(colorWhite),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = recipe.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(88.dp, 88.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}