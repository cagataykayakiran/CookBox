package com.example.recipeapp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BodyText(
    text: String,
    fontWeight: FontWeight = FontWeight.Medium,
    fontSize: TextUnit = 15.sp,
    maxLines: Int = 1,
    color: Color = Color.Black,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = fontWeight,
        fontSize = fontSize,
        maxLines = maxLines,
        color = color,
        overflow = overflow
    )
}