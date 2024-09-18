package com.example.recipeapp.presentation.search_screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.use_cases.SearchUseCase
import com.example.recipeapp.presentation.BaseViewModel
import com.example.recipeapp.presentation.search_screen.SearchScreenContract.UiAction
import com.example.recipeapp.presentation.search_screen.SearchScreenContract.UiEffect
import com.example.recipeapp.presentation.search_screen.SearchScreenContract.UiState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel<UiState, UiEffect, UiAction>(UiState()) {

    private var searchJob: Job? = null
    private val sharedPreferences = context.getSharedPreferences("search", Context.MODE_PRIVATE)

    init {
        updateSearchHistory()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnSearchQueryChanged -> {
                searchJob?.cancel()
                updateUiState { copy(isSearching = true) }
                searchJob = viewModelScope.launch {
                    search(uiAction.query)
                }
            }

            is UiAction.OnSearchResetClick -> {
                resetState()
            }

            is UiAction.OnRecipeClick -> {
                val recipeId = uiAction.recipeId
                viewModelScope.launch {
                    emitUiEffect(UiEffect.GotoDetail(recipeId))
                }
            }

            is UiAction.ChangeSearchQuery -> {
                updateUiState { copy(searchQuery = uiAction.query) }
            }

            is UiAction.ChangeIsSearching -> {
                updateUiState { copy(isSearching = uiAction.isSearching) }
            }

            is UiAction.ClearSearchHistory -> {
                clearSearchHistory()
            }

            is UiAction.SaveSearchHistory -> {
                saveSearchQuery(uiAction.query)
            }

            is UiAction.UpdateSearchHistory -> {
                updateSearchHistory()
            }

        }
    }

    private fun search(query: String) {
        searchUseCase(query)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        updateUiState {
                            copy(
                                data = result.data ?: emptyList(),
                                isLoading = false,
                                searchQuery = query
                            )
                        }
                    }

                    is Resource.Loading -> {
                        updateUiState {
                            copy(isLoading = true)
                        }
                        delay(2500)  // İsteğe bağlı gecikme
                    }

                    is Resource.Error -> {
                        updateUiState {
                            copy(
                                error = result.message ?: "Error!",
                                isLoading = false
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun saveSearchQuery(query: String) {
        if (query.isBlank()) return


        Log.d("SearchScreenViewModel", "Saving search query: $query")

        val historySet = sharedPreferences.getStringSet("historySet", emptySet()) ?: emptySet()
        val newHistorySet = HashSet(historySet)
        newHistorySet.add(query)

        sharedPreferences.edit().putStringSet("historySet", newHistorySet).apply()
        Log.d("SearchScreenViewModel", "Updated search history: $newHistorySet")
        updateSearchHistory()
    }


    private fun updateSearchHistory() {
        val historySet = sharedPreferences.getStringSet("historySet", mutableSetOf()) ?: mutableSetOf()
        Log.d("SearchScreenViewModel", "Current search history: $historySet")
        val historyList = historySet.toList().reversed()
        updateUiState { copy(searchHistory = historyList) }
    }

    private fun clearSearchHistory() {
        sharedPreferences.edit().remove("historySet").apply()
        updateSearchHistory()
    }

    private fun resetState() {
        updateUiState { UiState() }
    }
}

object SearchScreenContract {
    data class UiState(
        val isLoading: Boolean = false,
        val data: List<Recipe> = emptyList(),
        val error: String = "",
        val searchQuery: String = "",
        val isSearching: Boolean = false,
        val searchHistory: List<String> = emptyList()
    )

    sealed class UiAction {
        data class OnSearchQueryChanged(val query: String) : UiAction()
        data object OnSearchResetClick : UiAction()
        data class OnRecipeClick(val recipeId: Int) : UiAction()
        data class ChangeSearchQuery(val query: String) : UiAction()
        data class ChangeIsSearching(val isSearching: Boolean) : UiAction()
        data object ClearSearchHistory : UiAction()
        data class SaveSearchHistory(val query: String) : UiAction()
        data object UpdateSearchHistory: UiAction()
    }

    sealed class UiEffect {
        data class GotoDetail(val recipeId: Int) : UiEffect()
    }
}