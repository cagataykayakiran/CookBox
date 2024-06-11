package com.example.recipeapp.presentation.search_screen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.search_screen.component.SearchItem
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import com.example.recipeapp.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val state by viewModel.searchScreenState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize(),
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
                items(state.data) { recipe ->
                    SearchItem(recipe = recipe) {
                        navController.navigate(Screen.RecipeDetail.route + "/${recipe.id}")
                    }
                }
            }
        }
    }
}

