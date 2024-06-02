package com.example.myapplication.presentation.get_recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.get_recipes.RecipeState

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    state: RecipeState,
) {
}


@Composable
fun RecipeItem(modifier: Modifier = Modifier) {
    
    Box(modifier = modifier.padding(10.dp)){
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .width(250.dp)
                .height(160.dp)
                .clickable { },
            verticalArrangement =Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(8.dp))


        }
    }
    
}