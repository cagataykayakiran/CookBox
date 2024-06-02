package com.example.recipeapp.presentation.card_slider_section

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
import com.example.recipeapp.presentation.ui.theme.ButtonColorPrimary
import com.example.recipeapp.presentation.ui.theme.ButtonColorSecondary

@Composable
fun CardItem(navController: NavController, recipe: Recipe) {
    Card(
        modifier = Modifier
            .size(370.dp, 150.dp)
            .fillMaxHeight()
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = ButtonColorSecondary),
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
                    color = ButtonColorPrimary,
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