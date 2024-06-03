package com.example.recipeapp.presentation.search_screen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.presentation.search_screen.presentation.component.SearchItem
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier, viewModel: SearchViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val items = remember {
        mutableListOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
        )
    }
    val state by viewModel.searchScreenState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { focusManager.clearFocus() }),
        contentAlignment = Alignment.TopCenter
    ) {
        SearchBar(
            query = text,
            onQueryChange = {
                text = it
                viewModel.onEvent(SearchUiEvents.OnSearchQueryChanged(it))
            },
            onSearch = {
                active = false
                viewModel.onEvent(SearchUiEvents.OnSearchQueryChanged(text))
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (active) Icon(
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) text = "" else active = false
                        viewModel.onEvent(SearchUiEvents.OnSearchedResetClick)
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = BackgroundPrimary,
                inputFieldColors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black
                )
            )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(15.dp),
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(state.searchList) { recipe ->
                    SearchItem(recipe = recipe, viewModel::onEvent, isLoading = state.isLoading)
                }
            }
        }
    }
}


