package com.example.recipeapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {

    val text = buildAnnotatedString {
        append("Discovery Your \nFavourite ")
        withStyle(style = SpanStyle(color = MainColorPrimary)) {
            append("Recipes !")
        }
    }

    TopAppBar(
        modifier = modifier.padding(top = 15.dp),
        title = {
            Text(text = text)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}