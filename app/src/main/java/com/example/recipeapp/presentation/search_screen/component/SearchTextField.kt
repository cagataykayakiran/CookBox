package com.example.recipeapp.presentation.search_screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary

@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    onDone: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester
) {

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .focusRequester(focusRequester)
            .border(1.dp, MainColorPrimary, RoundedCornerShape(24.dp)),
        placeholder = { Text(text = "Search Recipes...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onReset() },
                    imageVector = Icons.Default.Close,
                    contentDescription = "close"
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            cursorColor = MainColorPrimary,
            focusedContainerColor = BackgroundPrimary,
            unfocusedContainerColor = BackgroundPrimary
        ),
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        )
    )
}