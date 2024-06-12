package com.example.recipeapp.presentation.search_screen

import SearchTextField
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.components.NavigationIcon
import com.example.recipeapp.presentation.search_screen.component.SearchHistoryBox
import com.example.recipeapp.presentation.search_screen.component.SearchResult
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    var query by remember { mutableStateOf("") }
    val state by viewModel.searchScreenState.collectAsState()
    var isSearching by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("history", Context.MODE_PRIVATE)


    Scaffold(
        modifier = Modifier.background(BackgroundPrimary).pointerInput(Unit) {
        detectTapGestures(onTap = {
            keyboardController?.hide()
            focusRequester.freeFocus()
        })
    },
        topBar = {
            TopAppBar(
                title = { Text(text = "Search") },
                navigationIcon = { NavigationIcon(navController = navController) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchTextField(
                query = query,
                onQueryChange = {
                    viewModel.onEvent(SearchUiEvents.OnSearchQueryChanged(it))
                    query = it
                    isSearching = true
                },
                onDone = {
                    viewModel.onEvent(SearchUiEvents.OnSearchQueryChanged(query))
                    sharedPreferences.edit().putString(query, query).apply()
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                },
                onReset = {
                    query = ""
                    viewModel.onEvent(SearchUiEvents.OnSearchedResetClick)
                },
                modifier = modifier,
                focusRequester = focusRequester
            )

            if (state.isLoading) {
                AnimatedPreloader()
            }
            val historyList = sharedPreferences.all.values.toList()

            if (!isSearching || query.isEmpty()) {
                SearchHistoryBox(historyList = historyList, onClick = { tag ->
                    query = tag
                    viewModel.onEvent(SearchUiEvents.OnSearchQueryChanged(query))
                    focusRequester.requestFocus()
                }, onClear = { sharedPreferences.edit().clear().apply() })
            } else {
                SearchResult(state.data, navController)
            }
        }
    }
}

