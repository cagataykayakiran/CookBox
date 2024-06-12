package com.example.recipeapp.work_manager

import com.example.recipeapp.data.local.entity.toRecipe
import com.example.recipeapp.domain.model.toRecipeEntity
import com.example.recipeapp.domain.repository.RecipeRepository

class DataSyncRepository(
    private val repository: RecipeRepository,
) {

    suspend fun syncData(): Int {
        val localRecipes = repository.getLocalRecipes().map { it.toRecipe() }

        val lowCaloriesRecipes = repository.getRecipesByLowCalories()
        val lowReadyTimeRecipes = repository.getRecipesByLowReadyTime()
        val highCaloriesRecipes = repository.getRecipesByHighProtein()
        val breakfastRecipes = repository.getPopularRecipes()

        val allRecipes =
            lowCaloriesRecipes + lowReadyTimeRecipes + highCaloriesRecipes + breakfastRecipes
        var newRecipeCount = 0
        for (recipe in allRecipes) {
            if (!localRecipes.contains(recipe)) {
                newRecipeCount++
                repository.insertRecipe(recipe.toRecipeEntity())
            }
        }
        return 1
    }
}