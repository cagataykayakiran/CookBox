package com.example.recipeapp.work_manager

import com.example.recipeapp.data.local.entity.toRecipe
import com.example.recipeapp.domain.model.toRecipeEntity
import com.example.recipeapp.domain.repository.RecipeRepository

class DataSyncRepository(
    private val repository: RecipeRepository,
) {

    suspend fun syncData(): Int {
        val existingMainRecipes = repository.getLocalRecipes().map { it.toRecipe() }
        val lowCaloriesRecipes = repository.getRecipesByLowCalories()
        val lowReadyTimeRecipes = repository.getRecipesByLowReadyTime()
        val highCaloriesRecipes = repository.getRecipesByHighProtein()
        val breakfastRecipes = repository.getRecipes()

        val allRecipes =
            lowCaloriesRecipes + lowReadyTimeRecipes + highCaloriesRecipes + breakfastRecipes
        var newRecipeCount = 0
        for (recipe in allRecipes) {
            if (!existingMainRecipes.contains(recipe)) {
                newRecipeCount++
                println(recipe.title)
                repository.insertRecipe(recipe.toRecipeEntity())
            }
        }
        println("DataSyncRepository $newRecipeCount")
        return newRecipeCount
    }
}