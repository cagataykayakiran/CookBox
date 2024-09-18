package com.example.recipeapp.presentation.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.components.AnimatedPreloader
import com.example.recipeapp.presentation.components.NavigationIcon
import com.example.recipeapp.presentation.search_screen.SearchScreenContract.UiAction
import com.example.recipeapp.presentation.search_screen.SearchScreenContract.UiState
import com.example.recipeapp.presentation.search_screen.SearchScreenContract.UiEffect
import com.example.recipeapp.presentation.search_screen.component.SearchHistoryBox
import com.example.recipeapp.presentation.search_screen.component.SearchResult
import com.example.recipeapp.presentation.search_screen.component.SearchTextField
import com.example.recipeapp.presentation.ui.theme.BackgroundPrimary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SearchScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navController: NavController,
    onNavigateDetail: (Int) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is UiEffect.GotoDetail -> {
                    onNavigateDetail(effect.recipeId)
                }
            }
        }
    }

    SearchScreenContent(
        query = uiState.searchQuery,
        isSearching = uiState.isSearching,
        isLoading = uiState.isLoading,
        onChangeQuery = { query -> onAction(UiAction.ChangeSearchQuery(query)) },
        onChangeIsSearching = { isSearching -> onAction(UiAction.ChangeIsSearching(isSearching)) },
        onResetQuery = { onAction(UiAction.OnSearchResetClick) },
        onAction = onAction,
        navController = navController,
        recipeList = uiState.data,
        searchHistory = uiState.searchHistory,
        onClearHistory = { onAction(UiAction.ClearSearchHistory) },
        saveHistory = { query -> onAction(UiAction.SaveSearchHistory(query)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit,
    navController: NavController,
    query: String,
    isLoading: Boolean,
    recipeList: List<Recipe>,
    onChangeQuery: (String) -> Unit,
    onChangeIsSearching: (Boolean) -> Unit,
    onResetQuery: () -> Unit,
    isSearching: Boolean,
    onClearHistory: () -> Unit,
    searchHistory: List<Any?>,
    saveHistory: (String) -> Unit
) {

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .background(BackgroundPrimary)
            .pointerInput(Unit) {
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
                onQueryChange = { query ->
                    onChangeQuery(query)
                    onAction(UiAction.ChangeIsSearching(true))
                    onAction(UiAction.OnSearchQueryChanged(query))
                },
                onDone = {
                    onAction(UiAction.OnSearchQueryChanged(query))
                    onAction(UiAction.SaveSearchHistory(query))
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                },
                onReset = {
                    onResetQuery()
                    onAction(UiAction.UpdateSearchHistory)
                },
                modifier = modifier,
                focusRequester = focusRequester
            )

            if (isLoading) {
                AnimatedPreloader()
            }

            if (!isSearching || query.isEmpty()) {
                SearchHistoryBox(
                    historyList = searchHistory,
                    onClick = { tag ->
                        onChangeQuery(tag)
                        onAction(UiAction.OnSearchQueryChanged(tag))
                        focusRequester.requestFocus()
                    },
                    onClear = { onClearHistory() })
            } else {
                SearchResult(recipeList, navController)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewProvider::class) uiState: UiState,
) {
    SearchScreen(
        uiState = uiState,
        onAction = {},
        navController = rememberNavController(),
        onNavigateDetail = {},
        uiEffect = emptyFlow()
    )
}