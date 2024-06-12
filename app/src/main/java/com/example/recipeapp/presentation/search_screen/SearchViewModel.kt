package com.example.recipeapp.presentation.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.util.Resource
import com.example.recipeapp.domain.use_cases.SearchUseCase
import com.example.recipeapp.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {
    private val _searchScreenState = MutableStateFlow(MainState.SearchState())
    val searchScreenState = _searchScreenState.asStateFlow()

    private var searchJob: Job? = null
    fun onEvent(event: SearchUiEvents) {
        when (event) {
            is SearchUiEvents.OnSearchQueryChanged -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    search(event.query)
                }
            }

            SearchUiEvents.OnSearchedResetClick -> resetState()
        }
    }

    private fun search(query: String) {
        searchUseCase.invoke(query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _searchScreenState.value =
                        _searchScreenState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false
                        )
                }

                is Resource.Loading -> {
                    _searchScreenState.value = _searchScreenState.value.copy(
                        isLoading = true
                    )
                    delay(2500)
                }

                is Resource.Error -> {
                    _searchScreenState.value =
                        _searchScreenState.value.copy(
                            error = result.message ?: "Error!",
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun resetState() {
        _searchScreenState.value = MainState.SearchState()
    }
}