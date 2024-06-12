package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.local.entity.RecipeEntity
import com.example.recipeapp.data.local.entity.RecipeDetailEntity
import com.example.recipeapp.data.local.RecipeWithIngredients
import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getPopularRecipes(): List<Recipe>

    suspend fun getTypeByRecipes(diet: String): List<Recipe>

    suspend fun getRecipesByLowCalories(): List<Recipe>

    suspend fun getRecipesByLowReadyTime(): List<Recipe>

    suspend fun getRecipesByHighProtein(): List<Recipe>

    suspend fun getRecipesById(id: Int): RecipeDetail

    suspend fun getLocalRecipeDetail(): Flow<List<RecipeDetailEntity>>

    suspend fun insertRecipeDetailWithIngredients(recipe: RecipeDetail, ingredients: List<ExtendedIngredient>, isFavorite: Boolean)

    suspend fun getRecipeWithIngredientsById(recipeId: Int): RecipeWithIngredients?

    suspend fun getLocalRecipes(): List<RecipeEntity>

    suspend fun insertRecipe(recipe: RecipeEntity)
}