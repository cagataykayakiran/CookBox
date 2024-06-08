package com.example.recipeapp.presentation.get_recipes_low_ready_time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.util.Resource
import com.example.recipeapp.domain.use_cases.GetRecipesByLowReadyTime
import com.example.recipeapp.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LowReadyTimeViewModel @Inject constructor(
    private val getRecipesByLowReadyTime: GetRecipesByLowReadyTime
): ViewModel() {

    private val _lowReadyTimeState = MutableStateFlow(MainState.LowReadyTimeState())
    val lowReadyTimeState = _lowReadyTimeState.asStateFlow()

    init {
        getMaxReadyTime()
    }

    private fun getMaxReadyTime() {
        getRecipesByLowReadyTime().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _lowReadyTimeState.value = _lowReadyTimeState.value.copy(
                        data = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _lowReadyTimeState.value = _lowReadyTimeState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _lowReadyTimeState.value = _lowReadyTimeState.value.copy(
                        error = result.message ?: "Error!",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}