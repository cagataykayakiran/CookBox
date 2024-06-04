package com.example.recipeapp.presentation.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.Resource
import com.example.recipeapp.domain.use_cases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {
    private val _searchScreenState = MutableStateFlow(SearchScreenState())
    val searchScreenState = _searchScreenState.asStateFlow()
    fun onEvent(event: SearchUiEvents) {
        when (event) {
            is SearchUiEvents.OnSearchQueryChanged -> search(event.query)
            is SearchUiEvents.OnSearchedItemClick -> {}
            SearchUiEvents.OnSearchedResetClick -> resetState()
        }
    }

    private fun search(query: String) {
        searchUseCase.invoke(query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _searchScreenState.value =
                        _searchScreenState.value.copy(
                            searchList = result.data ?: emptyList(),
                            isLoading = false
                        )
                }
                is Resource.Loading -> {
                    _searchScreenState.value = _searchScreenState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _searchScreenState.value =
                        _searchScreenState.value.copy(
                            error = result.message ?: "Error!",
                            isLoading = false
                        )
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun resetState() {
        _searchScreenState.value = SearchScreenState()
    }

}