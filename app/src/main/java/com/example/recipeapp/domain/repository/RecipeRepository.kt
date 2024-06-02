package com.example.recipeapp.domain.repository

import com.example.recipeapp.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipes(): List<Recipe>
    suspend fun getTypeByRecipes(diet: String): List<Recipe>
    //suspend fun upsertRecipes(recipes: List<RecipeEntity>)
    //fun getLocalDatabaseRecipe(): Flow<List<RecipeEntity>>
}