package com.example.recipeapp.data.repository

import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.response.toRecipeList
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi
): SearchRepository {
    override suspend fun getRecipeByName(query: String): List<Recipe> {
        return recipeApi.getRecipeByName(query).toRecipeList()
    }
}