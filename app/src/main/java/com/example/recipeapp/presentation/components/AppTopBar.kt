package com.example.recipeapp.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeapp.presentation.ui.theme.ButtonColorPrimary
import com.example.recipeapp.presentation.ui.theme.ButtonColorSecondary
import com.example.recipeapp.presentation.ui.theme.CardColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = "Recipe App")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ButtonColorPrimary
        )
    )
}