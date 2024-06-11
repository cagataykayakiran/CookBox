package com.example.recipeapp.domain.use_cases

import com.example.recipeapp.domain.model.ExtendedIngredient
import com.example.recipeapp.domain.model.RecipeDetail
import com.example.recipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class SaveDatabaseRecipe @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        recipe: RecipeDetail,
        ingredients: List<ExtendedIngredient>,
        isFavorite: Boolean) {
        recipeRepository.insertRecipeDetailWithIngredients(
            recipe, ingredients, isFavorite)
    }
}