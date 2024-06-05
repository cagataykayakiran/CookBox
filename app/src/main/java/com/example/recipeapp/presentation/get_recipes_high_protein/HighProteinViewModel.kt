package com.example.recipeapp.presentation.get_recipes_high_protein

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.use_cases.GetRecipesByHighProtein
import com.example.recipeapp.presentation.MainState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HighProteinViewModel @Inject constructor(
    private val getHighProteinUseCase: GetRecipesByHighProtein
): ViewModel() {

    private val _highProteinState = MutableStateFlow(MainState.HighProteinState())
    val highProteinState = _highProteinState.asStateFlow()

    init {
        getHighProtein()
    }

    private fun getHighProtein() {
        getHighProteinUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _highProteinState.value = _highProteinState.value.copy(
                        data = result.data ?: emptyList()
                    )
                }
                is Resource.Loading -> {
                    _highProteinState.value = _highProteinState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _highProteinState.value = _highProteinState.value.copy(
                        error = result.message ?: "Error!"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}