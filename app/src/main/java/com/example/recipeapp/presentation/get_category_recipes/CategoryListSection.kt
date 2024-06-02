package com.example.recipeapp.presentation.get_category_recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.recipeapp.presentation.get_recipes.RecipeState
import com.example.recipeapp.presentation.ui.theme.ButtonColorPrimary

@Composable
fun CategoryList(categories: List<String>, images: List<Int>) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val viewModel: RecipeTypeViewModel = hiltViewModel()

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(categories.size) { index ->
            CategoryItem(
                name = categories[index],
                iconResId = images[index],
                isSelected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    viewModel.onEvent(UIEvent.SelectCategory(categories[index]))
                }
            )
        }
    }
}


@Composable
fun CategoryItemList(modifier: Modifier = Modifier, state: RecipeState) {
    Box(modifier = modifier.size(300.dp, 300.dp)) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.fillMaxSize(),
        ) {
            items(state.recipe) { recipe ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(75.dp, 110.dp)
                        .background(Color.White)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White)
                                .size(60.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = state.recipe.map { },
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Text(
                            text = recipe.title,
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    name: String,
    iconResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .size(75.dp, 110.dp)
            .background(if (isSelected) ButtonColorPrimary else Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(50.dp)
                )
            }
            Text(
                text = name,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

