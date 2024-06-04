package com.example.recipeapp.domain.repository

import com.example.recipeapp.domain.model.Recipe

interface SearchRepository {

    suspend fun getRecipeByName(query: String): List<Recipe>
}