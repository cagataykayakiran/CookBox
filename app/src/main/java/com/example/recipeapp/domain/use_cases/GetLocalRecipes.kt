package com.example.recipeapp.domain.use_cases

import com.example.recipeapp.data.local.entity.RecipeDetailEntity
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalRecipes @Inject constructor(
    private val recipesRepository: RecipeRepository
) {

    operator fun invoke(): Flow<List<RecipeDetailEntity>> {
        return recipesRepository.getLocalRecipeDetail()
    }
}