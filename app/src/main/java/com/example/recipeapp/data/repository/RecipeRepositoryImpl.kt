package com.example.recipeapp.data.repository


import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.response.toRecipeDetail
import com.example.recipeapp.data.remote.response.toRecipeList
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.repository.RecipeRepository

import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return recipeApi.getBreakfastRecipeList().toRecipeList()
    }

    override suspend fun getTypeByRecipes(diet: String): List<Recipe> {
        return recipeApi.getDietTypeRecipeList(diet).toRecipeList()
    }

    override suspend fun getRecipesByLowCalories(): List<Recipe> {
        return recipeApi.getRecipesByLowCalories().toRecipeList()
    }

    override suspend fun getRecipesByLowReadyTime(): List<Recipe> {
        return recipeApi.getRecipesByLowReadyTime().toRecipeList()
    }

    override suspend fun getRecipesByHighProtein(): List<Recipe> {
        return recipeApi.getRecipesByHighProtein().toRecipeList()
    }

    override suspend fun getRecipesById(id: Int): RecipeDetail {
        return recipeApi.getRecipesById(id).toRecipeDetail()
    }
}