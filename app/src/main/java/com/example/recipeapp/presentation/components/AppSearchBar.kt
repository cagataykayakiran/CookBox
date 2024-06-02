package com.example.recipeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.ui.theme.ButtonColorPrimary

@Composable
fun AppSearchBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val hint by remember { mutableStateOf("Recipe Search...") }

    Row(
        modifier = modifier
            .fillMaxWidth().padding(top = 5.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = modifier,
            value = text,
            onValueChange = { text = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "search"
                )
            },
            placeholder = {
                Text(text = hint)
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            text = ""
                        },
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "close"
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )

        )
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(ButtonColorPrimary),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null, tint = Color.White)
        }
    }
}