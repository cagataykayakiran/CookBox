package com.example.recipeapp.domain.repository

import com.example.recipeapp.domain.model.Recipe

interface RecipeRepository {

    suspend fun getRecipes(): List<Recipe>
    suspend fun getTypeByRecipes(diet: String): List<Recipe>

    suspend fun getRecipesByLowCalories(): List<Recipe>

    suspend fun getRecipesByLowReadyTime(): List<Recipe>

    suspend fun getRecipesByHighProtein(): List<Recipe>
}