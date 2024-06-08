package com.example.recipeapp.presentation

import com.example.recipeapp.domain.model.Recipe

sealed interface MainState {
    val data: List<Recipe>
    val isLoading: Boolean
    val error: String
    data class CardRecipeState(
        override val data: List<Recipe> = emptyList(),
        override val isLoading: Boolean = false,
        override val error: String = ""
    ) : MainState

    data class RecipeTypeState(
        override val data: List<Recipe> = emptyList(),
        override val isLoading: Boolean = false,
        override val error: String = ""
    ) : MainState

    data class LowCaloriesState(
        override val data: List<Recipe> = emptyList(),
        override val isLoading: Boolean = false,
        override val error: String = ""
    ): MainState

    data class SearchState(
        override val data: List<Recipe> = emptyList(),
        override val isLoading: Boolean = false,
        override val error: String = "",
        val searchQuery: String = "",
    ): MainState

    data class LowReadyTimeState(
        override val data: List<Recipe> = emptyList(),
        override val isLoading: Boolean = false,
        override val error: String = "",
    ): MainState

    data class HighProteinState(
        override val data: List<Recipe> = emptyList(),
        override val isLoading: Boolean = false,
        override val error: String = "",
    ): MainState
}