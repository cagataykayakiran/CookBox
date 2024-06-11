package com.example.recipeapp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    text: String,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}