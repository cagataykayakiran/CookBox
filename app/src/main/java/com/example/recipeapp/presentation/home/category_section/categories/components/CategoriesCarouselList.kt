package com.example.recipeapp.presentation.home.category_section.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.recipeapp.presentation.components.BodyText
import com.example.recipeapp.presentation.home.category_section.categories.RecipeTypeContract
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.presentation.ui.theme.MainColorSecondary

@Composable
fun CategoriesCarouselList(
    categories: List<String>,
    images: List<Int>,
    onAction: (RecipeTypeContract.UiAction) -> Unit,
    onCategoryClick: (Int) -> Unit,
) {
    // Seçili kategori indexini hatırlıyoruz
    var selectedIndex by remember { mutableIntStateOf(-1) }

    // Yatay bir liste oluşturuyoruz
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
                    onAction(RecipeTypeContract.UiAction.SelectCategory(categories[index]))  // Tıklanan kategori için action gönderiyoruz
                    onCategoryClick(index)  // Tıklanan kategori index'ini geçiriyoruz
                }
            )
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
            .background(if (isSelected) MainColorSecondary else BackgroundPrimary)
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
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(70.dp)
                )
            }
            BodyText(
                text = name,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}